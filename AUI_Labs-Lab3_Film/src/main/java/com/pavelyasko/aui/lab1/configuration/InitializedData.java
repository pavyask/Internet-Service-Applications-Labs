package com.pavelyasko.aui.lab1.configuration;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.service.DirectorService;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializedData {
    private final FilmService filmService;

    private final DirectorService directorService;

    @Autowired
    public InitializedData(FilmService filmService, DirectorService directorService) {
        this.filmService = filmService;
        this.directorService = directorService;
    }

    @PostConstruct
    private synchronized void init() {
        Director jackson = Director.builder()
                .id(1L)
                .build();

        Director tarantino = Director.builder()
                .id(2L)
                .build();

        Director nolan = Director.builder()
                .id(3L)
                .build();

        directorService.save(jackson);
        directorService.save(tarantino);
        directorService.save(nolan);

        Film film1 = Film.builder()
                .title("The Lord of the Rings: The Fellowship of the Ring")
                .releaseYear(2001)
                .director(jackson)
                .build();

        Film film2 = Film.builder()
                .title("The Lord of the Rings: The Two Towers")
                .releaseYear(2002)
                .director(jackson)
                .build();

        Film film3 = Film.builder()
                .title("The Lord of the Rings: The Return of the King")
                .releaseYear(2003)
                .director(jackson)
                .build();

        Film film4 = Film.builder()
                .title("Pulp Fiction")
                .releaseYear(1994)
                .director(tarantino)
                .build();

        Film film5 = Film.builder()
                .title("Django Unchained")
                .releaseYear(2012)
                .director(tarantino)
                .build();

        Film film6 = Film.builder()
                .title("Once Upon a Time... in Hollywood")
                .releaseYear(2019)
                .director(tarantino)
                .build();

        Film film7 = Film.builder()
                .title("Interstellar")
                .releaseYear(2014)
                .director(nolan)
                .build();

        Film film8 = Film.builder()
                .title("Inception")
                .releaseYear(2010)
                .director(nolan)
                .build();

        Film film9 = Film.builder()
                .title("The Prestige")
                .releaseYear(2006)
                .director(nolan)
                .build();

        filmService.save(film1);
        filmService.save(film2);
        filmService.save(film3);
        filmService.save(film4);
        filmService.save(film5);
        filmService.save(film6);
        filmService.save(film7);
        filmService.save(film8);
        filmService.save(film9);
    }
}
