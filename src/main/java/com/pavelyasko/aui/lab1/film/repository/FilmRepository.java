package com.pavelyasko.aui.lab1.film.repository;

import com.pavelyasko.aui.lab1.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
