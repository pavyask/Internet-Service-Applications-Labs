package com.pavelyasko.aui.lab1.film.repository;

import com.pavelyasko.aui.lab1.datastore.DataStore;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class FilmRepository implements Repository<Film, Long> {

    private DataStore store;

    @Autowired
    public FilmRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Film> find(Long id) {
        return store.findFilm(id);
    }

    @Override
    public List<Film> findAll() {
        return store.findAllFilms();
    }

    @Override
    public void save(Film entity) {
        store.saveFilm(entity);
    }

    @Override
    public void delete(Film entity) {
        store.deleteFilm(entity.getId());
    }
}
