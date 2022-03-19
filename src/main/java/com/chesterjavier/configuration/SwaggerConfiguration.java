package com.chesterjavier.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * Created by chesterjavier
 */

@Configuration
public class SwaggerConfiguration {

  private ApiInfo apiInfo() {
    return new ApiInfo("Sample REST API: Suburbs Names and Postcodes",
        "A simple REST API in Spring Boot that accepts a list of Suburb Names and Postcodes in the HTTP request body, and persist the data in a database (either in memory or a traditional one)  also have an endpoint that receives the postcode range and returns in the response body the list of Suburb Names belonging to that postcode range, sorted alphabetically as well as the total number of characters of all names combined.",
        "",
        "",
        new Contact("Chester Javier", "https://chesterjavier03.com", "chesterjavier03@gmail.com"),
        "", "", Collections.emptyList());
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .build();
  }

  @Bean
  UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
        .deepLinking(true)
        .displayOperationId(false)
        .defaultModelsExpandDepth(1)
        .defaultModelExpandDepth(1)
        .defaultModelRendering(ModelRendering.EXAMPLE)
        .displayRequestDuration(false)
        .docExpansion(DocExpansion.NONE)
        .filter(false)
        .maxDisplayedTags(null)
        .operationsSorter(OperationsSorter.ALPHA)
        .showExtensions(false)
        .tagsSorter(TagsSorter.ALPHA)
        .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
        .validatorUrl(null)
        .build();
  }
}
