package com.easyjob.jobmanager.util;

public class SecurityConstants {

    public static final String SIGNIN_KEY = "XQ6f9vsj2zAm6SL2debE2SEVMBakKp9mk2BKAjKy";
    public static final long EXPIRATION_TIME = 60 * 60 * 36000;
    public static final String TOKEN_HEADER = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Authorization";
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/vacancy/all", "/vacancy/find", "/vacancy/find/**", "/resume/all", "/resume/find", "/resume/find/**"};

    private SecurityConstants() {
    }
}
