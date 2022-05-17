package com.mw.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


public class AccountDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountEmailDto {
        @Email(message = "이메일 형식이 아닙니다.")
        String email;
    }

    @Builder
    public static class AccountCodeDto {
        Integer code;
    }
}
