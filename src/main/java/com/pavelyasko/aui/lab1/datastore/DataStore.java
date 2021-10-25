package com.pavelyasko.aui.lab1.datastore;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.serialization.CloningUtility;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {
    private Set<Film> films = new HashSet<>();

    private Set<Director> directors = new HashSet<>();

    public synchronized List<Film> findAllFilms() {
        return films.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Film> findFilm(Long id) {
        return films.stream()
                .filter(film -> film.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void saveFilm(Film film) throws IllegalArgumentException {
        film.setId(findAllFilms().stream()
                .mapToLong(Film::getId)
                .max().orElse(0) + 1);
        films.add(CloningUtility.clone(film));
    }


    public synchronized void deleteFilm(Long id) throws IllegalArgumentException {
        findFilm(id).ifPresentOrElse(
                original -> films.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The film with id \"%d\" does not exist", id));
                });
    }


    public synchronized List<Director> findAllDirectors() {
        return directors.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Director> findDirector(Long id) {
        return directors.stream()
                .filter(director -> director.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void saveDirector(Director director) throws IllegalArgumentException {
        director.setId(findAllDirectors().stream()
                .mapToLong(Director::getId)
                .max().orElse(0) + 1);
        directors.add(CloningUtility.clone(director));
    }

    public synchronized void deleteDirector(Long id) throws IllegalArgumentException {
        findDirector(id).ifPresentOrElse(
                original -> directors.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", id));
                });
    }
}
