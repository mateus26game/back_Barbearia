package com.teste.tes.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfing   {

    @Bean  // faz com que o Spring registre esse objeto no contexto da aplicação.
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Barbearia API REST").version("1.0.0")
                .license(new License().name("repositório do projeto").url("https://github.com/mateus26game/back_Barbearia"))
        );
    }
}
