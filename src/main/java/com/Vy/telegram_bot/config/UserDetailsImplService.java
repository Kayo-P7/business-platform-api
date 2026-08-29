package com.Vy.telegram_bot.config;

import com.Vy.telegram_bot.exception.UserNotFoundException;
import com.Vy.telegram_bot.model.User;
import com.Vy.telegram_bot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*#1*/
@Service
public class UserDetailsImplService implements UserDetailsService {//encontra esse usuário


    private final UserRepository userRepository;

    public UserDetailsImplService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("Email not found"));
            return new UserDetailsImpl(user);

    }
}
