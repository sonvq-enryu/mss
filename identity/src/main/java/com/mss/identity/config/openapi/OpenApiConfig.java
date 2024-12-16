package com.mss.identity.config.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public ModelResolver ModelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
}
