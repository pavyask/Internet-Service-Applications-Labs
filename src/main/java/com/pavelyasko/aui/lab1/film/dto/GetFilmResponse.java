package com.pavelyasko.aui.lab1.film.dto;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilmResponse {
    private Long id;

    private String title;

    private int releaseYear;

//    private String directorFullName;

    public static Function<Film, GetFilmResponse> entityToDtoMapper() {
        return film -> GetFilmResponse.builder()
                .id(film.getId())
                .title(film.getTitle())
                .releaseYear(film.getReleaseYear())
//                .directorFullName(film.getDirector().getName()+" "+film.getDirector().getSurname())
                .build();
    }
}
