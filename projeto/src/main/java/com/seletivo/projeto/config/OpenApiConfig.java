package com.seletivo.projeto.config;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.seletivo.projeto.utils.ReadJsonFileToJsonObject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@OpenAPIDefinition
@Configuration
@SecurityScheme(name = "token", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfig {

	@Bean
	public OpenAPI baseOpenAPI() throws IOException {

		ReadJsonFileToJsonObject readJsonFileToJsonObject = new ReadJsonFileToJsonObject();

		

		return new OpenAPI().addServersItem(new Server())
				.info(new Info().title("Api Neki")
						.version("V0.0.1")
						.description("API Neki")
						.contact(new Contact().name("Neki")));
	}

}