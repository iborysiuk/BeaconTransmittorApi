package com.beacon.transmitter.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Yuriy on 2016-07-07.
 */
@Configuration
public class CoreConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
