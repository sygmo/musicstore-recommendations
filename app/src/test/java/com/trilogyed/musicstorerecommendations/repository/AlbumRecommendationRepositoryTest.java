package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {

    @Autowired
    AlbumRecommendationRepository albumRecommendationRepository;

    @Before
    public void setUp() throws Exception {
        albumRecommendationRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        albumRecommendationRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetDeleteAlbumRec() {
        // Create
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(2);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);

        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);
        Optional<AlbumRecommendation> foundAlbumRec = albumRecommendationRepository.findById(albumRecommendation.getId());

        assertTrue(foundAlbumRec.isPresent());
        assertEquals(albumRecommendation, foundAlbumRec.get());

        // Get and Update
        albumRecommendation.setLiked(false);

        albumRecommendationRepository.save(albumRecommendation);
        foundAlbumRec = albumRecommendationRepository.findById(albumRecommendation.getId());

        assertTrue(foundAlbumRec.isPresent());
        assertEquals(albumRecommendation, foundAlbumRec.get());

        // Delete
        albumRecommendationRepository.deleteById(albumRecommendation.getId());
        foundAlbumRec = albumRecommendationRepository.findById(albumRecommendation.getId());

        assertFalse(foundAlbumRec.isPresent());
    }

    @Test
    public void shouldFindAllAlbumRecs() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(2);
        albumRecommendation.setUserId(1);
        albumRecommendation.setLiked(true);

        AlbumRecommendation albumRecommendation1 = new AlbumRecommendation();
        albumRecommendation1.setAlbumId(4);
        albumRecommendation1.setUserId(2);
        albumRecommendation1.setLiked(false);

        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);
        albumRecommendation1 = albumRecommendationRepository.save(albumRecommendation1);
        List<AlbumRecommendation> allAlbumRecs = new ArrayList<>();
        allAlbumRecs.add(albumRecommendation);
        allAlbumRecs.add(albumRecommendation1);

        List<AlbumRecommendation> foundAllAlbumRecs = albumRecommendationRepository.findAll();

        assertEquals(allAlbumRecs.size(), foundAllAlbumRecs.size());
    }

}