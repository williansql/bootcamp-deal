package com.bootcamp.dio.projeto_bootcamp.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private Contact contato() {
        return new Contact()
                .name("Willians Lisardo")
                .url("https://github.com/WilliansLisardo")
                .email("willslipknotsp@gmail.com");
    }

    private Info infoApi() {
        return new Info()
                .title("Projeto Bootcamp")
                .description("Projeto Bootcamp")
                .version("1.0.0")
                .termsOfService("OpenSource")
                .contact(contato());
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(infoApi());
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("projeto-bootcamp")
                .pathsToMatch("/**")
                .build();
    }
}
