package com.lucas.embeddedh2test.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    // http://localhost:8080/swagger-ui/index.html

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Spring h2 test Application")
                        .version("1.0.0")
                        .description("Spring h2 test Application"));
    }
}
