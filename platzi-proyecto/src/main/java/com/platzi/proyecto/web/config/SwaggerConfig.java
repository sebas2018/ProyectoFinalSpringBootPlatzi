package com.platzi.proyecto.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //habilitamos EnableSwagger2
public class SwaggerConfig {

    //incluimos el siguiente bean
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)//especificamos el tipo de documentacion que va a implementar
                //le especificamos que se quiere visualiozar en la documentacion
                .select()
                //le especificamos el nombre del paquete donde estan los controladores:
                .apis(RequestHandlerSelectors.basePackage("com.platzi.proyecto.web.controller"))
                .build();
    }

}
