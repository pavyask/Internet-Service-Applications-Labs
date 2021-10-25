package com.pavelyasko.aui.lab1.director.entity;


import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
@Entity
@Table(name = "directors")
public class Director implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ToString.Exclude
    @OneToMany(mappedBy = "director")
    private List<Film> films;
}