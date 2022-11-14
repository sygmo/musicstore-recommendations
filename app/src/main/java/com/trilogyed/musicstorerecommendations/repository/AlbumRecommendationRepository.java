package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Long> {

}
