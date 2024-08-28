package com.example.BookstoreAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(false) // Disables path extensions
                .favorParameter(true) // Enables query parameters
                .parameterName("format") // Use `format` as the query parameter
                .ignoreAcceptHeader(false) // Prioritizes the Accept header
                .defaultContentType(MediaType.APPLICATION_JSON) // Default to JSON if no format is specified
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
