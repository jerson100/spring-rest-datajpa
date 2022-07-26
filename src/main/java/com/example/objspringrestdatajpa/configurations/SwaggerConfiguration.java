package com.example.objspringrestdatajpa.configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.Collections;

/**
 * Spring lee el @Configuration
 * Gracias al @SpringBootAplication spring ya tiene registrado un scaneo de los paquetes
 * y va creando los objetos de las clases que tengan anotaciones
 * y esta clase va a ser scaneada por spring
 * Configuración Swagger para la generación de documentación de la API REST
 *
 * http://localhost:8080/swagger-ui/index.html
 */
//@Configuration
public class SwaggerConfiguration {

    /*
    * Para que al momento de que arrance Spring lea este método
    * y gracias a la anotación @Bean sabrá que es un bean y que tiene que manejarlo
    * e ejecutarlo y crear una instancia de Docket para así usarlo cuando se inyecte en algún lado
    * que lo necesite, en este caso en el paquete springfox
    * */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/public/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring boot book api rest")
                        .description("Library api rest docs")
                        .version("v0.0.1")
                        .license(new License().name("MIT").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }


//    private ApiInfo apiDetails(){
//        return new ApiInfo("Spring boot book api rest",
//                "Library api rest docs",
//                "1.0",
//                "https://www.google.com",
//                new Contact("Jerson",
//                        "http://www.google.com",
//                        "juamkoo@gmail.com"),
//                "MIT",
//                "http://www.google.com",
//                Collections.emptyList());
//    }


}
