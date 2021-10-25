package com.pavelyasko.aui.lab1.director.dto;

import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
}
