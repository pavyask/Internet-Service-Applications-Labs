package com.pavelyasko.aui.lab1.film.entity;

import com.pavelyasko.aui.lab1.director.entity.Director;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "films")
public class Film implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name="director")
    private Director director;
}
