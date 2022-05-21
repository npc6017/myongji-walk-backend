package com.mw.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema
        @Email(message = "이메일 형식이 아닙니다.")
        String email;
    }

    @Getter
    public static class AccountCodeDto {
        @Schema
        String email;
        @Schema
        Integer code;
    }

    @Getter
    public static class AccountInfoDto {
        @Schema
        String email;
        @Schema
        String password;
    }
}
