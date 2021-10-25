package com.pavelyasko.aui.lab1.director.service;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.repository.DirectorRepository;
import com.pavelyasko.aui.lab1.film.entity.Film;
import com.pavelyasko.aui.lab1.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private DirectorRepository repository;

    @Autowired
    public DirectorService(DirectorRepository repository) {
        this.repository = repository;
    }

    public Optional<Director> find(Long id) {
        return repository.find(id);
    }

    public List<Director> findAll() {
        return repository.findAll();
    }

    public void save(Director director) {
        repository.save(director);
    }

    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }
}
