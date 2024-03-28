package com.loginsystem.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ironman2023 API Documentation - 2023 IT鐵人賽範例")
                        .description("SpringBoot 3.x application")
                        .version("v0.0.1")
                );
    }

}