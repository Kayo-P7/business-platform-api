package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.Request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/*#2*/
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String auth(LoginRequest request) {

        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken
                                /*
                                 * Representa as credenciais apresentadas pelo usuário
                                 * e é enviada ao AuthenticationManager para tentar autenticar.
                                 * Ele não autentica sozinho, Ele apenas cria um objeto que representa principal e credentials.
                                 */
                                (
                                        request.email(),
                                        request.password()
                                ));

        return jwtService.generatedToken(authentication);//geração de token
    }

}
