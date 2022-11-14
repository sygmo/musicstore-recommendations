package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "track_recommendation")
public class TrackRecommendation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_recommendation_id")
    private long id;
    @Column(name = "track_id")
    @NotNull(message = "Track id is required")
    private long trackId;
    @Column(name = "user_id")
    @NotNull(message = "User id for track rec is required")
    private long userId;
    @NotNull(message = "Liked for track rec is required")
    private boolean liked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
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
        TrackRecommendation that = (TrackRecommendation) o;
        return getId() == that.getId() && getTrackId() == that.getTrackId() && getUserId() == that.getUserId() && isLiked() == that.isLiked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrackId(), getUserId(), isLiked());
    }

    @Override
    public String toString() {
        return "TrackRecommendation{" +
                "id=" + id +
                ", trackId=" + trackId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
