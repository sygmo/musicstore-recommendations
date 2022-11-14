package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist_recommendation")
public class ArtistRecommendation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_recommendation_id")
    private long id;
    @Column(name = "artist_id")
    @NotNull(message = "Artist id is required")
    private long artistId;
    @Column(name = "user_id")
    @NotNull(message = "User id for artist rec is required")
    private long userId;
    @NotNull(message = "Liked for artist rec is required")
    private boolean liked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistRecommendation that = (ArtistRecommendation) o;
        return getId() == that.getId() && getArtistId() == that.getArtistId() && getUserId() == that.getUserId() && isLiked() == that.isLiked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArtistId(), getUserId(), isLiked());
    }

    @Override
    public String toString() {
        return "ArtistRecommendation{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
