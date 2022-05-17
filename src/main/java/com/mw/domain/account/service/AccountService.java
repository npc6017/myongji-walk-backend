package com.mw.domain.account.service;

import com.mw.domain.account.entity.Account;
import com.mw.domain.account.repository.AccountRepository;
import com.mw.domain.dto.AccountDto;
import com.mw.domain.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService {
    private final MailService mailService;
    private final AccountRepository accountRepository;

    @Transactional
    public void validateEmail(AccountDto.AccountEmailDto accountEmailDto) {
        String email = accountEmailDto.getEmail();
        validateMyoungJiPeople(email);

        Account account = accountRepository.findAccountByEmail(accountEmailDto.getEmail());
        if (account != null) throw new RuntimeException();
        else account = Account.builder()
                .email(accountEmailDto.getEmail())
                .build();

        Integer code = account.randomizeCode();

        // 이메일로 코드 보내기.
        mailService.mailSend(new MailDto(account.getEmail(), code.toString()));

        accountRepository.save(account);
    }

    private void validateMyoungJiPeople(String email) {
        String[] split = email.split("@");
        // 이메일형식에 안맞는거임 예외 던지기
        if(split.length != 2) throw new RuntimeException();
        // 명지대학교 학생이 아닌거야 예외 던지기
        if(!split[1].equals("mju.ac.kr")) throw new RuntimeException();
    }
}
