package com.sparta.house_backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 오리진을 설정
        config.addAllowedOrigin("http://localhost:3001");
        config.addAllowedOrigin("http://localhost:4000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/api/**", config); // 경로에 따라 설정을 다르게 할 수 있음

        // CorsFilter 생성자에 매개변수를 추가하여 수정
        return new CorsFilter(source);
    }
}
