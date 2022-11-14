package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
import com.trilogyed.musicstorerecommendations.repository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/artistRec")
public class ArtistRecommendationController {

    @Autowired
    ArtistRecommendationRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistRec(@RequestBody @Valid ArtistRecommendation artistRecommendation) {
        if (artistRecommendation == null) {
            throw new IllegalArgumentException("Artist recommendation object is null");
        }

        return repository.save(artistRecommendation);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistRec(@PathVariable long id) {
        ArtistRecommendation artistRecommendation = repository.findById(id).orElse(null);
        if (artistRecommendation == null) {
            throw new IllegalArgumentException("Artist rec could not be found for id: " + id);
        } else {
            return artistRecommendation;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRec(@RequestBody @Valid ArtistRecommendation artistRecommendation) {
        if (artistRecommendation == null || artistRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Artist rec must have a valid id");
        } else if (getArtistRec(artistRecommendation.getId()) == null) {
            throw new IllegalArgumentException("Artist rec does not exist to update");
        } else {
            repository.save(artistRecommendation);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRec(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecs() {
        List<ArtistRecommendation> artistRecommendations = repository.findAll();
        if (artistRecommendations == null || artistRecommendations.isEmpty()) {
            throw new IllegalArgumentException("No artist recs were found");
        } else {
            return artistRecommendations;
        }
    }
}
