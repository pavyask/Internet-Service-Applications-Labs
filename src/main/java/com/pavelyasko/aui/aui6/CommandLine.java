package com.pavelyasko.aui.aui6;

import com.pavelyasko.aui.aui6.file.service.CustomFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine implements CommandLineRunner {

    private CustomFileService fileService;

    @Autowired
    public CommandLine(CustomFileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void run(String... args) {
    }
}
