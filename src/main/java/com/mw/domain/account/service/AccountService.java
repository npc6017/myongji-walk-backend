package com.mw.domain.account.service;

import com.mw.domain.account.entity.Account;
import com.mw.domain.account.repository.AccountRepository;
import com.mw.domain.dto.AccountDto;
import com.mw.domain.dto.MailDto;
import com.mw.exception.custom.*;
import com.mw.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final MailService mailService;
    private final AccountRepository accountRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(AccountDto.AccountInfoDto accountInfoDto) {
        Account account = accountRepository.findAccountByEmail(accountInfoDto.getEmail()).orElseThrow(AccountNotFoundException::new);
        account.inputDetails(accountInfoDto.getPassword());
    }

    public AccountDto.TokenDto signIn(AccountDto.AccountInfoDto accountInfoDto) {
        Account account = accountRepository.findAccountByEmail(accountInfoDto.getEmail()).orElseThrow(AccountNotFoundException::new);

        if (!account.validatePassword(accountInfoDto.getPassword())) throw new PasswordIncorrectException();

        return AccountDto.TokenDto.builder()
                .accessToken(jwtProvider.createAccessToken(account))
                .build();
    }


    public void validateCode(AccountDto.AccountCodeDto accountCodeDto) {
        Account account = accountRepository.findAccountByEmail(accountCodeDto.getEmail()).orElseThrow(AccountNotFoundException::new);
        if (!account.validateCode(accountCodeDto.getCode())) throw new CodeNotEqualException();
    }

    @Transactional
    public void validateEmail(AccountDto.AccountEmailDto accountEmailDto) {
        String email = accountEmailDto.getEmail();
        Account account;
        validateMyoungJiPeople(email);

        Optional<Account> accountOptional = accountRepository.findAccountByEmail(accountEmailDto.getEmail());
        if (accountOptional.isPresent()) throw new AccountAlreadyExistException();
        else account = Account.builder()
                .email(accountEmailDto.getEmail())
                .build();

        Integer code = account.randomizeCode();

        mailService.mailSend(new MailDto(account.getEmail(), code.toString()));
        accountRepository.save(account);
    }

    private void validateMyoungJiPeople(String email) {
        String[] split = email.split("@");
        if (split.length != 2) throw new NotMyoungJiMemberException();
        if (!split[1].equals("mju.ac.kr")) throw new NotInEmailFormatException();
    }
}
