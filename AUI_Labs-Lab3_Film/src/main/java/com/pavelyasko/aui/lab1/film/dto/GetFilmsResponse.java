package com.pavelyasko.aui.lab1.film.dto;

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
public class GetFilmsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Film {

        private Long id;

        private String title;

    }

    @Singular
    private List<Film> films;

    public static Function<Collection<com.pavelyasko.aui.lab1.film.entity.Film>, GetFilmsResponse> entityToDtoMapper() {
        return films -> {
            GetFilmsResponseBuilder response = GetFilmsResponse.builder();
            films.stream()
                    .map(film -> Film.builder()
                            .id(film.getId())
                            .title(film.getTitle())
                            .build())
                    .forEach(response::film);
            return response.build();
        };
    }

}
