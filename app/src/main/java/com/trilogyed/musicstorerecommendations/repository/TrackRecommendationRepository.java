package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Long> {

}
