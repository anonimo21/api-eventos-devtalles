package com.gestion.eventos.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

        private static final String SCHEME_NAME = "bearerAuth";
        private static final String BEARER_FORMAT = "JWT";
        private static final String DESCRIPTION = "JWT Authentication para la API de Gestión de Eventos";

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                                .components(new Components()
                                                .addSecuritySchemes(SCHEME_NAME,
                                                                new SecurityScheme()
                                                                                .name(SCHEME_NAME)
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat(BEARER_FORMAT)
                                                                                .in(SecurityScheme.In.HEADER)
                                                                                .description(DESCRIPTION))

                                )
                                .info(new io.swagger.v3.oas.models.info.Info()
                                                .title("API de Gestión de Eventos")
                                                .version("1.0")
                                                .description("API para gestionar eventos, usuarios y autenticación JWT")
                                                .contact(new Contact()
                                                                .name("Eder Montiel")
                                                                .email("eder.montiel@example.com")
                                                                .url("https://github.com/edermontiel"))
                                                .license(new License()
                                                                .name("MIT License")
                                                                .url("https://opensource.org/licenses/MIT")));
        }

}
