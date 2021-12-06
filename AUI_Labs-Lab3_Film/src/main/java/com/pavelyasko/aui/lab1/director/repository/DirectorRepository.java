package com.pavelyasko.aui.lab1.director.repository;

import com.pavelyasko.aui.lab1.director.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
