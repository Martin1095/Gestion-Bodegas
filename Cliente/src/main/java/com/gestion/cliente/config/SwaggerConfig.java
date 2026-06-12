package com.gestion.cliente.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API deGestión de clientes")
                        .version("1.0")
                        .description("API para la gestión de clientes, incluyendo información de contacto y historial de compras."));
    }
}
