package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
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
public class ArtistRecommendationRepositoryTest {

    @Autowired
    ArtistRecommendationRepository artistRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        artistRecommendationRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        artistRecommendationRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetDeleteArtistRec() {
        // Create
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(2);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);

        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);
        Optional<ArtistRecommendation> foundArtistRec = artistRecommendationRepository.findById(artistRecommendation.getId());

        assertTrue(foundArtistRec.isPresent());
        assertEquals(artistRecommendation, foundArtistRec.get());

        // Get and Update
        artistRecommendation.setLiked(false);

        artistRecommendationRepository.save(artistRecommendation);
        foundArtistRec = artistRecommendationRepository.findById(artistRecommendation.getId());

        assertTrue(foundArtistRec.isPresent());
        assertEquals(artistRecommendation, foundArtistRec.get());

        // Delete
        artistRecommendationRepository.deleteById(artistRecommendation.getId());
        foundArtistRec = artistRecommendationRepository.findById(artistRecommendation.getId());

        assertFalse(foundArtistRec.isPresent());
    }

    @Test
    public void shouldFindAllArtistRecs() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(2);
        artistRecommendation.setUserId(1);
        artistRecommendation.setLiked(true);

        ArtistRecommendation artistRecommendation1 = new ArtistRecommendation();
        artistRecommendation1.setArtistId(4);
        artistRecommendation1.setUserId(2);
        artistRecommendation1.setLiked(false);

        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);
        artistRecommendation1 = artistRecommendationRepository.save(artistRecommendation1);
        List<ArtistRecommendation> allArtistRecs = new ArrayList<>();
        allArtistRecs.add(artistRecommendation);
        allArtistRecs.add(artistRecommendation1);

        List<ArtistRecommendation> foundAllArtistRecs = artistRecommendationRepository.findAll();

        assertEquals(allArtistRecs.size(), foundAllArtistRecs.size());
    }

}