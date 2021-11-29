package com.pavelyasko.aui.lab1.director.dto;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateDirectorRequest {

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    //    private List<Film> films;

    public static BiFunction<Director, UpdateDirectorRequest, Director> dtoToEntityUpdater() {
        return (director, request) -> {
            director.setName(request.getName());
            director.setSurname(request.getSurname());
            director.setDateOfBirth(request.getDateOfBirth());
            return director;
        };
    }
}
