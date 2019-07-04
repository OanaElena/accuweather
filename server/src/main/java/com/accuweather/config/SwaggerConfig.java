package com.accuweather.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.accuweather.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Accuweather API SpringBoot Application",
                "For Swagger to show the correct endpoints, must provide the method type in @RequestMapping of each method, otherwise will create automatically for each method, each method type 'PUT<DELETE<PATCH etc' ",
                "Accuweather API",
                "",
                 new Contact("DANESCU OANA-ELENA", "localhost:9000/swagger-ui.html", ""),
                "",
                "",
                Collections.emptyList()
        );
    }
}
