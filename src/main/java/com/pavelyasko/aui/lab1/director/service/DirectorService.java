package com.pavelyasko.aui.lab1.director.service;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repository.findById(id);
    }

    public List<Director> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(Director director) {
        repository.save(director);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Director director) {
        repository.save(director);
    }
}
