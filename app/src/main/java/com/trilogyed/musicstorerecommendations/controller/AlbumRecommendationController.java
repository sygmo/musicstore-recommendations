package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
import com.trilogyed.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/albumRec")
public class AlbumRecommendationController {

    @Autowired
    AlbumRecommendationRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRec(@RequestBody @Valid AlbumRecommendation albumRecommendation) {
        if (albumRecommendation == null) {
            throw new IllegalArgumentException("Album recommendation object is null");
        }

        return repository.save(albumRecommendation);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRec(@PathVariable long id) {
        AlbumRecommendation albumRecommendation = repository.findById(id).orElse(null);
        if (albumRecommendation == null) {
            throw new IllegalArgumentException("Album rec could not be found for id: " + id);
        } else {
            return albumRecommendation;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRec(@RequestBody @Valid AlbumRecommendation albumRecommendation) {
        if (albumRecommendation == null || albumRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Album rec must have a valid id");
        } else if (getAlbumRec(albumRecommendation.getId()) == null) {
            throw new IllegalArgumentException("Album rec does not exist to update");
        } else {
            repository.save(albumRecommendation);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRec(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecs() {
        List<AlbumRecommendation> albumRecommendations = repository.findAll();
        if (albumRecommendations == null || albumRecommendations.isEmpty()) {
            throw new IllegalArgumentException("No album recs were found");
        } else {
            return albumRecommendations;
        }
    }
}
