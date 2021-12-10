package com.pavelyasko.aui.lab1.film.controller;


import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.service.DirectorService;
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
@RequestMapping("api/directors/{directorID}/films")
public class DirectorFilmController {


    private FilmService filmService;

    private DirectorService directorService;

    @Autowired
    public DirectorFilmController(FilmService filmService, DirectorService directorService) {
        this.filmService = filmService;
        this.directorService = directorService;
    }


    @GetMapping
    public ResponseEntity<GetFilmsResponse> getFilms(@PathVariable("directorID") long id) {
        Optional<Director> director = directorService.find(id);
        return director.map(value -> ResponseEntity.ok(GetFilmsResponse.entityToDtoMapper().apply(filmService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("{filmID}")
    public ResponseEntity<GetFilmResponse> getFilm(@PathVariable("directorID") long directorID,
                                                   @PathVariable("filmID") long filmID) {
        return filmService.find(directorID, filmID)
                .map(value -> ResponseEntity.ok(GetFilmResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createFilm(@PathVariable("directorID") long directorID,
                                           @RequestBody CreateFilmRequest request,
                                           UriComponentsBuilder builder) {
        Optional<Director> director = directorService.find(directorID);
        if (director.isPresent()) {
            Film film = CreateFilmRequest
                    .dtoToEntityMapper(director::get)
                    .apply(request);
            film = filmService.save(film);
            return ResponseEntity.created(builder.pathSegment("api", "directors", "{directorID}", "films", "{filmID}")
                    .buildAndExpand(director.get().getId(), film.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{filmID}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("directorID") long directorID,
                                           @PathVariable("filmID") long filmID) {
        Optional<Film> film = filmService.find(directorID, filmID);
        if (film.isPresent()) {
            filmService.delete(film.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{filmID}")
    public ResponseEntity<Void> updateFilm(@PathVariable("directorID") long directorID,
                                           @RequestBody UpdateFilmRequest request,
                                           @PathVariable("filmID") long filmID) {
        Optional<Film> film = filmService.find(directorID, filmID);
        if (film.isPresent()) {
            UpdateFilmRequest.dtoToEntityUpdater().apply(film.get(), request);
            filmService.update(film.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
