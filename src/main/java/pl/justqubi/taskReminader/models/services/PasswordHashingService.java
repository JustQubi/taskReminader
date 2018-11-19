package pl.justqubi.taskReminader.models.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashingService {
    public String hash(String password){
        return getPasswordEncoder().encode(password);
    }

    public boolean matches(String password, String hash){
        return getPasswordEncoder().matches(password, hash);
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

