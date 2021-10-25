package com.pavelyasko.aui.lab1.film.dto;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateFilmRequest {

    private String title;

    private int releaseYear;

    public static Function<CreateFilmRequest, Film> dtoToEntityMapper(
            Supplier<Director> directorSupplier) {
        return request -> Film.builder()
                .title(request.getTitle())
                .releaseYear(request.getReleaseYear())
                .director(directorSupplier.get())
                .build();
    }
}
