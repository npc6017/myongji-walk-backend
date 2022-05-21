package com.mw.domain.account.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private Integer code;

    @Builder
    public Account(String email, Integer code) {
        this.email = email;
        this.code = code;
    }

    public Integer randomizeCode() {
        Random random = new Random();
        this.code = random.nextInt(1000000);
        return this.code;
    }

    public boolean validateCode(Integer code) {
        return this.code.equals(code);
    }

    public void inputDetails(String password) {
        this.password = password;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
