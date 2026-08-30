package com.Vy.telegram_bot.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
/*#2*/
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    //openssl rand -base64 32 -> terminal Linux
    //Base64 é definido por um conjunto específico de caracteres e regras de tamanho.
    /*#2*/
    private SecretKey getSigningKey() {
        byte[] KeyBytes = Decoders.BASE64.decode(secret);
        //decode() recupera os bytes
        return Keys.hmacShaKeyFor(KeyBytes);
        //hmacShaKeyFor() transforma os bytes em uma SecretKey.
    }

    /*#2*/
    public String generatedToken(Authentication authentication) {
        Date now = new Date();
        return Jwts.builder()
                .subject(authentication.getName())/*define quem é o usuario
                *se conecta com o userDetailsImpl(getName)
                *
                */

                .issuedAt(now)
                .expiration(new Date(now.getTime() + 3600000))
                .signWith(getSigningKey())//assina usando a chave
                .compact();//finaliza e produz o JWT

    }

    public String extractUserName(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

//    public void showClaims(String token) {
//
//        var claims = Jwts.parser()
//                .verifyWith(getSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//
//        System.out.println(claims);
//    }
}
