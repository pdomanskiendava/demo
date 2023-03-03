package com.endava.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest template config. We add proper Accept header and Bearer token to AWS calls
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        rest.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Accept","application/vnd.github+json");
            request.getHeaders().setBearerAuth("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");
            return execution.execute(request, body);
        });
        return rest;
    }
}
