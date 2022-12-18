package com.getir.readingisgood.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    @Value("${apiinfo.title}")
    private String title;
    @Value("${apiinfo.description}")
    private String description;
    @Value("${apiinfo.fullName}")
    private String fullName;
    @Value("${apiinfo.version}")
    private String version;
    @Value("${apiinfo.email}")
    private String email;
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                title,
                description,
                version,
                null,
                new Contact(fullName,null, email),
                "License of API", "API license URL", Collections.emptyList());
    }
}
