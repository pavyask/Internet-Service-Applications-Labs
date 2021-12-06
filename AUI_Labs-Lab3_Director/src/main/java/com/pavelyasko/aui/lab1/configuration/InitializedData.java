package com.pavelyasko.aui.lab1.configuration;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializedData {


    private final DirectorService directorService;

    @Autowired
    public InitializedData(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostConstruct
    private synchronized void init() {
        Director jackson = Director.builder()
                .name("Peter")
                .surname("Jackson")
                .dateOfBirth(LocalDate.of(1961, 10, 31))
                .build();

        Director tarantino = Director.builder()
                .name("Quentin")
                .surname("Tarantino")
                .dateOfBirth(LocalDate.of(1963, 3, 27))
                .build();

        Director nolan = Director.builder()
                .name("Christopher")
                .surname("Nolan")
                .dateOfBirth(LocalDate.of(1970, 7, 30))
                .build();

        directorService.save(jackson);
        directorService.save(tarantino);
        directorService.save(nolan);
    }

}
