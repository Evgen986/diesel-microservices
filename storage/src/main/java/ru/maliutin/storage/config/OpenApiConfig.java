package ru.maliutin.storage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Бин конфигурации документации Swagger.
     * @return подготовленный бин.
     */
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Складское API")
                .description("Манипуляции с товарами")
                .version("0.001"));
    }

}
