package com.pavelyasko.aui.lab1.film.entity;

import com.pavelyasko.aui.lab1.director.entity.Director;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Film implements Serializable {
    /**
     * Unique id (primary key).
     */
    private Long id;

    private String title;

    private int releaseYear;

    private Director director;
}
