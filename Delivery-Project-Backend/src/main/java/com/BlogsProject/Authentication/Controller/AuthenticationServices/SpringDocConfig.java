package com.BlogsProject.Authentication.Controller.AuthenticationServices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SpringDocConfig {

    public GroupedOpenApi conversationsOpenApi() {
        return GroupedOpenApi.builder().group("Dapinacchio APIs").pathsToMatch(
                "/api/v1/auth/register",
                "/api/v1/auth/authenticate",
                "/api/v1/auth/refreshToken"
        ).build();
    }
}