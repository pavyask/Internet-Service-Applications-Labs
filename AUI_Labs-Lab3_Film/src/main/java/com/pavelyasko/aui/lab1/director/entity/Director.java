package com.pavelyasko.aui.lab1.director.entity;


import com.pavelyasko.aui.lab1.film.entity.Film;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
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
    private Long id;

    @ToString.Exclude
    @OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE)
    private List<Film> films;
}