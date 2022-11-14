package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
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
public class TrackRecommendationRepositoryTest {

    @Autowired
    TrackRecommendationRepository trackRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        trackRecommendationRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        trackRecommendationRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetDeleteTrackRec() {
        // Create
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(2);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);

        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);
        Optional<TrackRecommendation> foundTrackRec = trackRecommendationRepository.findById(trackRecommendation.getId());

        assertTrue(foundTrackRec.isPresent());
        assertEquals(trackRecommendation, foundTrackRec.get());

        // Get and Update
        trackRecommendation.setLiked(false);

        trackRecommendationRepository.save(trackRecommendation);
        foundTrackRec = trackRecommendationRepository.findById(trackRecommendation.getId());

        assertTrue(foundTrackRec.isPresent());
        assertEquals(trackRecommendation, foundTrackRec.get());

        // Delete
        trackRecommendationRepository.deleteById(trackRecommendation.getId());
        foundTrackRec = trackRecommendationRepository.findById(trackRecommendation.getId());

        assertFalse(foundTrackRec.isPresent());
    }

    @Test
    public void shouldFindAllTrackRecs() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(2);
        trackRecommendation.setUserId(1);
        trackRecommendation.setLiked(true);

        TrackRecommendation trackRecommendation1 = new TrackRecommendation();
        trackRecommendation1.setTrackId(4);
        trackRecommendation1.setUserId(2);
        trackRecommendation1.setLiked(false);

        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);
        trackRecommendation1 = trackRecommendationRepository.save(trackRecommendation1);
        List<TrackRecommendation> allTrackRecs = new ArrayList<>();
        allTrackRecs.add(trackRecommendation);
        allTrackRecs.add(trackRecommendation1);

        List<TrackRecommendation> foundAllTrackRecs = trackRecommendationRepository.findAll();

        assertEquals(allTrackRecs.size(), foundAllTrackRecs.size());
    }

}