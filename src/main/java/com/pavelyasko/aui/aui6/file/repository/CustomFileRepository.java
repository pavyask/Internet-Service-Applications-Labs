package com.pavelyasko.aui.aui6.file.repository;

import com.pavelyasko.aui.aui6.file.entity.CustomFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomFileRepository extends JpaRepository<CustomFile, Long> {

    Optional<CustomFile> findByFileName(String filePath);

}