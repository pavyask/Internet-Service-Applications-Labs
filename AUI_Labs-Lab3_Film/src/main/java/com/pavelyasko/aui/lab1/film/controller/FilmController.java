package com.pavelyasko.aui.lab1.film.controller;


import com.pavelyasko.aui.lab1.film.dto.CreateFilmRequest;
import com.pavelyasko.aui.lab1.film.dto.GetFilmResponse;
import com.pavelyasko.aui.lab1.film.dto.GetFilmsResponse;
import com.pavelyasko.aui.lab1.film.dto.UpdateFilmRequest;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<GetFilmsResponse> getFilms() {
        return ResponseEntity
                .ok(GetFilmsResponse.entityToDtoMapper().apply(filmService.findAll()));
    }


    @GetMapping("{id}")
    public ResponseEntity<GetFilmResponse> getFilm(@PathVariable("id") long id) {
        return filmService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetFilmResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") long id) {
        Optional<Film> film = filmService.find(id);
        if (film.isPresent()) {
            filmService.delete(film.get().getId());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


    @PostMapping
    public ResponseEntity<Void> createFilm(@RequestBody CreateFilmRequest request, UriComponentsBuilder builder) {
        Film film = CreateFilmRequest.dtoToEntityMapper(() -> null).apply(request);
        film = filmService.save(film);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "films", "{id}")
                        .buildAndExpand(film.getId()).toUri())
                .build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Void> updateFilm(@RequestBody UpdateFilmRequest request, @PathVariable("id") long id) {
        Optional<Film> film = filmService.find(id);
        if (film.isPresent()) {
            UpdateFilmRequest.dtoToEntityUpdater().apply(film.get(), request);
            filmService.update(film.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
