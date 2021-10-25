package com.pavelyasko.aui.lab1.film.dto;

import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFilmRequest {

    private String title;

    private int releaseYear;

    public static BiFunction<Film, UpdateFilmRequest, Film> dtoToEntityUpdater() {
        return (film, request) -> {
            film.setTitle(request.getTitle());
            film.setReleaseYear(request.getReleaseYear());
            return film;
        };
    }
}
