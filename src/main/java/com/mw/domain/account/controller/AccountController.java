package com.mw.domain.account.controller;

import com.mw.domain.account.service.AccountService;
import com.mw.domain.dto.AccountDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이메일 인증 API", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/valid")
    public ResponseEntity<String> getCodeByEmail(@Valid @RequestBody AccountDto.AccountEmailDto accountEmailDto) {
        accountService.validateEmail(accountEmailDto);
        return ResponseEntity.status(HttpStatus.OK).body("Good");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "code 인증 API", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/code")
    public ResponseEntity<String> validateCode(@RequestBody AccountDto.AccountCodeDto accountCodeDto) {
        accountService.validateCode(accountCodeDto);
        return ResponseEntity.status(HttpStatus.OK).body("Good");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증 후 회원가입 API", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody AccountDto.AccountInfoDto accountInfoDto) {
        accountService.signUp(accountInfoDto);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 API", content = @Content(schema = @Schema(implementation = AccountDto.TokenDto.class)))
    })
    @PostMapping("/signIn")
    public ResponseEntity<AccountDto.TokenDto> signIn(AccountDto.AccountInfoDto accountInfoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.signIn(accountInfoDto));
    }
}
