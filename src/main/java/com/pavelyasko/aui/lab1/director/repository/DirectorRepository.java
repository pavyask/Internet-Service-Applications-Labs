package com.pavelyasko.aui.lab1.director.repository;

import com.pavelyasko.aui.lab1.datastore.DataStore;
import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class DirectorRepository implements Repository<Director, Long> {

    private DataStore store;

    @Autowired
    public DirectorRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Director> find(Long id) {
        return store.findDirector(id);
    }

    @Override
    public List<Director> findAll() {
        return store.findAllDirectors();
    }

    @Override
    public void save(Director entity) {
        store.saveDirector(entity);
    }

    @Override
    public void delete(Director entity) {
        store.deleteDirector(entity.getId());
    }
}
