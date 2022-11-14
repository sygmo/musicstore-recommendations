package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRecommendationRepository extends JpaRepository<LabelRecommendation, Long> {

}
