package com.parsakav.echorestapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;


@Configuration

public class SpringFoxConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");

/*
        Server prodServer = new Server();
        prodServer.setUrl("http://localhost:8080");
        prodServer.setDescription("Server URL in Production environment");
*/

        Contact contact = new Contact();
        contact.setEmail("parsakavianpour@gmail.com");
        contact.setName("Parsakav");
        contact.setUrl("https://www.github.com/parsakav");

        //License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://www.bezkoder.com/terms");
                //.license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}

