package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRecommendationRepository extends JpaRepository<ArtistRecommendation, Long> {

}
