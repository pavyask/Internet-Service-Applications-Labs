package com.pavelyasko.aui.lab1.director.dto;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateDirectorRequest {

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

//    private List<Film> films;

    public static Function<CreateDirectorRequest, Director> dtoToEntityMapper() {
        return request -> Director.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }
}
