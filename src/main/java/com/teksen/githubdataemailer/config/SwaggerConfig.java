package com.teksen.githubdataemailer.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("Github Data Emailer API")
                .version("1.0")
                .description("Github Data Emailer API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0")
                .termsOfService("http://swagger.io/terms")
                .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org"))
                .contact(new io.swagger.v3.oas.models.info.Contact().name("Erenalp Tekşen").email("erenalpteksen01@gmail.com"))

        );
    }
}
