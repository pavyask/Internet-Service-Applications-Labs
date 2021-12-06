package com.pavelyasko.aui.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("directors", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/directors/{directorID}", "/api/directors")
                        .uri("http://localhost:8081"))
                .route("films", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/films", "/api/films/**", "/api/directors/{directorID}/films", "/api/directors/{directorID}/films/**")
                        .uri("http://localhost:8082"))
                .build();
    }

}
