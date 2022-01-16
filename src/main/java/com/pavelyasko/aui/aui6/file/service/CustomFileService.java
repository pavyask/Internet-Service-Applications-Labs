package com.pavelyasko.aui.aui6.file.service;

import com.pavelyasko.aui.aui6.file.entity.CustomFile;
import com.pavelyasko.aui.aui6.file.repository.CustomFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomFileService {

    private CustomFileRepository fileRepository;

    @Autowired
    public CustomFileService(CustomFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Optional<CustomFile> find(Long id) {
        return fileRepository.findById(id);
    }

    public List<CustomFile> findAll() {
        return fileRepository.findAll();
    }

    @Transactional
    public void create(CustomFile file) {
        fileRepository.save(file);
    }

    @Transactional
    public void create(String author, String description, String filename) {
        CustomFile file = CustomFile.builder()
                .author(author)
                .description(description)
                .fileName(filename)
                .build();
        fileRepository.save(file);
    }
}
