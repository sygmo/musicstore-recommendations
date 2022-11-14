package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trilogyed.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/labelRec")
public class LabelRecommendationController {

    @Autowired
    LabelRecommendationRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRec(@RequestBody @Valid LabelRecommendation labelRecommendation) {
        if (labelRecommendation == null) {
            throw new IllegalArgumentException("Label recommendation object is null");
        }

        return repository.save(labelRecommendation);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRec(@PathVariable long id) {
        LabelRecommendation labelRecommendation = repository.findById(id).orElse(null);
        if (labelRecommendation == null) {
            throw new IllegalArgumentException("Label rec could not be found for id: " + id);
        } else {
            return labelRecommendation;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRec(@RequestBody @Valid LabelRecommendation labelRecommendation) {
        if (labelRecommendation == null || labelRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Label rec must have a valid id");
        } else if (getLabelRec(labelRecommendation.getId()) == null) {
            throw new IllegalArgumentException("Label rec does not exist to update");
        } else {
            repository.save(labelRecommendation);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRec(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecs() {
        List<LabelRecommendation> labelRecommendations = repository.findAll();
        if (labelRecommendations == null || labelRecommendations.isEmpty()) {
            throw new IllegalArgumentException("No label recs were found");
        } else {
            return labelRecommendations;
        }
    }
}
