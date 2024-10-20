package org.example.hotelmanagementsystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("Hotel management system")
                        .description("""
                                The program creates 2 Users when launched. Admin a@gmail.com password root123,
                                Customer b@gmail.com password root123.
                                Creates 2 rooms and 1 order.
                                Upon registration
                                A Confirm Token arrives and a code is sent by email.
                                When confirming the code via email,
                                you need to add header Key="Confirm" and
                                Value=the token that was received during registration to the request
                                """)
                        .version("1.0").contact(new Contact().name("Asilbek Norkobilov")
                                .email( "asilbek2001.05@gmail.com").url("https://t.me/crazy_001"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
