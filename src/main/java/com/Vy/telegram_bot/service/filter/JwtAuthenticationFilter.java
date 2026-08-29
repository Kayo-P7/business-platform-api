package com.Vy.telegram_bot.service.filter;

import com.Vy.telegram_bot.config.UserDetailsImplService;
import com.Vy.telegram_bot.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*#4*/
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //pega o jwt da requisição
    private final JwtService jwtService;
    private final UserDetailsImplService userDetailsImplService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);//ele tenta continua, mas o return faz ele parar de executar fazendo o método terminar
            return;
        }

        String jwt = authHeader.substring(7);
        String userName = jwtService.extractUserName(jwt);
//        jwtService.showClaims(jwt);

        /*#2*/
        UserDetails userDetails = userDetailsImplService.loadUserByUsername(userName);
        /*#4*/
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);//continue. A autenticação já foi processada pela cadeia do spring security
        //É apenas um mecanismo para passar a requisição adiante, ele não verifica

    }
}
