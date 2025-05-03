package com.teste.tes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");


    }

}

//"O CORS fica louco porque você tá rodando dois servidores na sua máquina"

// cros falado - “Ué?? Você já tá num servidor, pra que tá tentando mexer em outro??
// Isso tá meio suspeito…”

// “Relaxa, navegador... esse cara da porta 4200 sou eu mesmo, só tô separando
// o frontend do backend aqui na minha máquina. Pode deixar passar!”

//“Ahhh, tá bom então. Confio, vai lá.” 😂