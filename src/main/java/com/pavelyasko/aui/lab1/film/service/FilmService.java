package com.pavelyasko.aui.lab1.film.service;

import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private FilmRepository repository;

    @Autowired
    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public Optional<Film> find(Long id) {
        return repository.find(id);
    }

    public List<Film> findAll() {
        return repository.findAll();
    }

    public void save(Film film) {
        repository.save(film);
    }

    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }
}
