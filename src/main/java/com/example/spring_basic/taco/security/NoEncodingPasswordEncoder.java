package com.example.spring_basic.taco.security;

import org.springframework.security.crypto.password.PasswordEncoder;

@Deprecated public class NoEncodingPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
