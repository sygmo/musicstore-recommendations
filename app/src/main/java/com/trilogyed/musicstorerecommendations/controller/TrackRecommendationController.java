package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
import com.trilogyed.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/trackRec")
public class TrackRecommendationController {

    @Autowired
    TrackRecommendationRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRec(@RequestBody @Valid TrackRecommendation trackRecommendation) {
        if (trackRecommendation == null) {
            throw new IllegalArgumentException("Track recommendation object is null");
        }

        return repository.save(trackRecommendation);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRec(@PathVariable long id) {
        TrackRecommendation trackRecommendation = repository.findById(id).orElse(null);
        if (trackRecommendation == null) {
            throw new IllegalArgumentException("Track rec could not be found for id: " + id);
        } else {
            return trackRecommendation;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRec(@RequestBody @Valid TrackRecommendation trackRecommendation) {
        if (trackRecommendation == null || trackRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Track rec must have a valid id");
        } else if (getTrackRec(trackRecommendation.getId()) == null) {
            throw new IllegalArgumentException("Track rec does not exist to update");
        } else {
            repository.save(trackRecommendation);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRec(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecs() {
        List<TrackRecommendation> trackRecommendations = repository.findAll();
        if (trackRecommendations == null || trackRecommendations.isEmpty()) {
            throw new IllegalArgumentException("No track recs were found");
        } else {
            return trackRecommendations;
        }
    }
}
