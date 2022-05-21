package com.mw.domain.security;

import com.mw.domain.account.entity.Account;
import com.mw.domain.account.repository.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtProvider {
    @Value("${jwt.accessTokenExpiration}") private static Long accessExpiration;
    @Value("${jwt.secret}") private static String secretKey;
    private final AccountRepository accountRepository;

    public String createAccessToken(Account account) {
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpiration);

        return Jwts.builder()
                .setSubject(account.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
