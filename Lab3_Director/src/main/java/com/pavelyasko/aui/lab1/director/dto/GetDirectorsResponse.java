package com.pavelyasko.aui.lab1.director.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDirectorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Director {

        private Long id;

        private String name;

        private String surname;

    }

    @Singular
    private List<Director> directors;

    public static Function<Collection<com.pavelyasko.aui.lab1.director.entity.Director>, GetDirectorsResponse> entityToDtoMapper() {
        return directors -> {
            GetDirectorsResponseBuilder response = GetDirectorsResponse.builder();
            directors.stream()
                    .map(director -> Director.builder()
                            .id(director.getId())
                            .name(director.getName())
                            .surname(director.getSurname())
                            .build())
                    .forEach(response::director);
            return response.build();
        };
    }

}