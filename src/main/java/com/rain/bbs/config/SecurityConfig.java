package com.rain.bbs.config;

import com.rain.bbs.security.CredentialsDigest;
import com.rain.bbs.security.SHA1CredentialsDigest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public CredentialsDigest credentialsDigest(){
        return new SHA1CredentialsDigest();
    }
}
