package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LabelRecommendationRepositoryTest {

    @Autowired
    LabelRecommendationRepository labelRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        labelRecommendationRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        labelRecommendationRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetDeleteLabelRec() {
        // Create
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(2);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);

        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);
        Optional<LabelRecommendation> foundLabelRec = labelRecommendationRepository.findById(labelRecommendation.getId());

        assertTrue(foundLabelRec.isPresent());
        assertEquals(labelRecommendation, foundLabelRec.get());

        // Get and Update
        labelRecommendation.setLiked(false);

        labelRecommendationRepository.save(labelRecommendation);
        foundLabelRec = labelRecommendationRepository.findById(labelRecommendation.getId());

        assertTrue(foundLabelRec.isPresent());
        assertEquals(labelRecommendation, foundLabelRec.get());

        // Delete
        labelRecommendationRepository.deleteById(labelRecommendation.getId());
        foundLabelRec = labelRecommendationRepository.findById(labelRecommendation.getId());

        assertFalse(foundLabelRec.isPresent());
    }

    @Test
    public void shouldFindAllLabelRecs() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(2);
        labelRecommendation.setUserId(1);
        labelRecommendation.setLiked(true);

        LabelRecommendation labelRecommendation1 = new LabelRecommendation();
        labelRecommendation1.setLabelId(4);
        labelRecommendation1.setUserId(2);
        labelRecommendation1.setLiked(false);

        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);
        labelRecommendation1 = labelRecommendationRepository.save(labelRecommendation1);
        List<LabelRecommendation> allLabelRecs = new ArrayList<>();
        allLabelRecs.add(labelRecommendation);
        allLabelRecs.add(labelRecommendation1);

        List<LabelRecommendation> foundAllLabelRecs = labelRecommendationRepository.findAll();

        assertEquals(allLabelRecs.size(), foundAllLabelRecs.size());
    }

}