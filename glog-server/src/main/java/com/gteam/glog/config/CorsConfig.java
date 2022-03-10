package com.gteam.glog.config;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Log4j2
public class CorsConfig {

    @Bean
    public CorsFilter CorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowCredentials(false);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://**");
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization","x-user-id"));

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
