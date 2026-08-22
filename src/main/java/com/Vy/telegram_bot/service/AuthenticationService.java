package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.Request.LoginRequest;
import com.Vy.telegram_bot.model.User;
import com.Vy.telegram_bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public Authentication auth(LoginRequest request) {


        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken/*Representa as credenciais antes daa autenticação. Ele inicia a tentatica de busca*/(
                request.email(),
                request.password()
        ));

    }

}
