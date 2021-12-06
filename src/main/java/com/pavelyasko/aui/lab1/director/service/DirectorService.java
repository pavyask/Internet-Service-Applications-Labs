package com.pavelyasko.aui.lab1.director.service;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.event.repository.DirectorEventRepository;
import com.pavelyasko.aui.lab1.director.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private DirectorRepository directorRepository;

    private DirectorEventRepository eventRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, DirectorEventRepository eventRepository) {
        this.directorRepository = directorRepository;
        this.eventRepository = eventRepository;
    }

    public Optional<Director> find(Long id) {
        return directorRepository.findById(id);
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Transactional
    public void save(Director director) {
        directorRepository.save(director);
        eventRepository.create(director);
    }

    @Transactional
    public void delete(Director director) {
        eventRepository.delete(director);
        directorRepository.delete(director);
    }

    @Transactional
    public void update(Director director) {
        directorRepository.save(director);
    }
}
