package com.pavelyasko.aui.lab1.film.service;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.repository.DirectorRepository;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    private DirectorRepository directorRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, DirectorRepository directorRepository) {
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    public Optional<Film> find(Long id) {
        return filmRepository.findById(id);
    }

    public Optional<Film> find(Long directorID, Long filmID) {
        Optional<Director> director = directorRepository.findById(directorID);
        if (director.isPresent()) {
            return filmRepository.findByIdAndDirector(filmID, director.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public List<Film> findAll(Director director) {
        return filmRepository.findAllByDirector(director);
    }

    @Transactional
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    public void delete(Long id) {
        filmRepository.deleteById(id);
    }

    @Transactional
    public void update(Film film) {
        filmRepository.save(film);
    }
}
