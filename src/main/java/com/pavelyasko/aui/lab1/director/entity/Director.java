package com.pavelyasko.aui.lab1.director.entity;


import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Director implements Serializable {
    /**
     * Unique id (primary key).
     */
    private Long id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

//    @ToString.Exclude
//    private List<Film> films;
}