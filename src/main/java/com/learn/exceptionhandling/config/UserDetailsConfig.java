package com.learn.exceptionhandling.config;

import com.learn.exceptionhandling.security.BlahUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new BlahUserDetailsService();
    }
}
