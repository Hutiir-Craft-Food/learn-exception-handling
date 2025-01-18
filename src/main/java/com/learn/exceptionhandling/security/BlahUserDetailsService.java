package com.learn.exceptionhandling.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class BlahUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("ghost")) {
            throw new UsernameNotFoundException("User not found");
        }

        String role = username.equals("admin") ? "ROLE_ADMIN" : "ROLE_USER";
        String password = "$2a$10$kwmHEij.UQuvr9czDGkHe.XjWYLOYBF5obiYhi9AJnRI6cV6NQ1Eq";
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        return new User(username, password, authorities);
    }
}
