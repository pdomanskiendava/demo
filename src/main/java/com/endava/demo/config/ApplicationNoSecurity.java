package com.endava.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Basic Web configuration. We go mostly on defaults
 */
@Configuration
@EnableWebSecurity
public class ApplicationNoSecurity {

    /**
     * Remove authentication from all endpoints
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }
//    @Bean
//    public WebMvcConfigurer customConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//                configurer.defaultContentType(MediaType.APPLICATION_JSON);
//            }
//        };
//    }
}