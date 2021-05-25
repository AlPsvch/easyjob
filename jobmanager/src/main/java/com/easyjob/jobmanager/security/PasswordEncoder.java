package com.easyjob.jobmanager.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "passwordEncoder")
public class PasswordEncoder extends BCryptPasswordEncoder {

    /*@Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }*/
}
