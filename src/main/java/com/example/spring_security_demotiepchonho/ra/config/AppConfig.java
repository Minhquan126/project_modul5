package com.example.spring_security_demotiepchonho.ra.config;

import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DateTimeZone timeZone() {
        return DateTimeZone.forID("Asia/Ho_Chi_Minh");
    }
}