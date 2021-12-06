package com.pavelyasko.aui.lab1.director.controller;

import com.pavelyasko.aui.lab1.director.dto.CreateDirectorRequest;
import com.pavelyasko.aui.lab1.director.dto.GetDirectorResponse;
import com.pavelyasko.aui.lab1.director.dto.GetDirectorsResponse;
import com.pavelyasko.aui.lab1.director.dto.UpdateDirectorRequest;
import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/directors")
public class DirectorController {

    private DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<GetDirectorsResponse> getDirectors() {
        return ResponseEntity.ok(GetDirectorsResponse.entityToDtoMapper().apply(directorService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetDirectorResponse> getDirector(@PathVariable("id") long id) {
        return directorService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetDirectorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("id") long id) {
        Optional<Director> director = directorService.find(id);
        if (director.isPresent()) {
            directorService.delete(director.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createDirector(@RequestBody CreateDirectorRequest request, UriComponentsBuilder builder) {
        Director director = CreateDirectorRequest.dtoToEntityMapper().apply(request);
        directorService.save(director);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "directors", "{id}")
                        .buildAndExpand(director.getId()).toUri())
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateDirector(@RequestBody UpdateDirectorRequest request, @PathVariable("id") long id) {
        Optional<Director> director = directorService.find(id);
        if (director.isPresent()) {
            UpdateDirectorRequest.dtoToEntityUpdater().apply(director.get(), request);
            directorService.update(director.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
