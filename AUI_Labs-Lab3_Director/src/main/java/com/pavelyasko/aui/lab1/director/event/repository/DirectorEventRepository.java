package com.pavelyasko.aui.lab1.director.event.repository;

import com.pavelyasko.aui.lab1.director.entity.Director;
import com.pavelyasko.aui.lab1.director.event.dto.CreateDirectorEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DirectorEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public DirectorEventRepository(@Value("${film.base.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Director director) {
        restTemplate.delete("/directors/{directorID}", director.getId());
    }

    public void create(Director director) {
        restTemplate.postForLocation("/directors", CreateDirectorEventRequest.entityToDtoMapper().apply(director));
    }
}