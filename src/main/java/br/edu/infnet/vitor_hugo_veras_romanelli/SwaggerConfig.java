package br.edu.infnet.vitor_hugo_veras_romanelli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API: locadora de filmes")
                .version("1.0")
                .description("API para gestão de filmes")
                .contact(new Contact()
                    .name("Equipe de Desenvolvimento")
                    .email("dev@infnet.com")));
    }
}