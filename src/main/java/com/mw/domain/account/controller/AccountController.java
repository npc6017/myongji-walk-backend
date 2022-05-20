package com.mw.domain.account.controller;

import com.mw.domain.account.service.AccountService;
import com.mw.domain.dto.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/valid")
    public ResponseEntity<String> getCodeByEmail(@Valid @RequestBody AccountDto.AccountEmailDto accountEmailDto) {
        accountService.validateEmail(accountEmailDto);
        return ResponseEntity.status(HttpStatus.OK).body("Good");
    }

    @PostMapping("/code")
    public ResponseEntity<String> validateCode(@RequestBody AccountDto.AccountCodeDto accountCodeDto) {
        accountService.validateCode(accountCodeDto);
        return ResponseEntity.status(HttpStatus.OK).body("Good");
    }

    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody AccountDto.AccountInfoDto accountInfoDto) {
        accountService.signUp(accountInfoDto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
