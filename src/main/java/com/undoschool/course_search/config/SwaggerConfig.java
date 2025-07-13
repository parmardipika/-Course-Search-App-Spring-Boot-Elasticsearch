package com.undoschool.course_search.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI courseSearchOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Course Search API")
                        .description("Search and filter courses with Elasticsearch backend")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dipika Parmar")
                                .email("parmardipika61825@gmail.com")
                                .url("https://github.com/parmardipika"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
