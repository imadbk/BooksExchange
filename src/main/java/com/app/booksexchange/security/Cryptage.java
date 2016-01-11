package com.app.booksexchange.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Cryptage {

    public static String enBCrypt(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwd);  
    }

}
