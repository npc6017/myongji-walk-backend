package com.mw.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MailDto {

    private final String DEFAULT_TITLE = "인증 코드 입니다.";

    private String address;
    private String title = DEFAULT_TITLE;
    private String code;

    public MailDto(String address, String code) {
        this.address = address;
        this.code = code;
    }
}
