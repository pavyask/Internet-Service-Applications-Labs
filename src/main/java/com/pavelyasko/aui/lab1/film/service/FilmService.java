package com.pavelyasko.aui.lab1.film.service;

import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repository.findById(id);
    }

    public List<Film> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(Film film) {
        repository.save(film);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Film film) {
        repository.save(film);
    }
}
