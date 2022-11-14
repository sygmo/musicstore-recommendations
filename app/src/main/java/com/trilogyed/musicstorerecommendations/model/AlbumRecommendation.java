package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "album_recommendation")
public class AlbumRecommendation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_recommendation_id")
    private long id;
    @Column(name = "album_id")
    @NotNull(message = "Album id is required")
    private long albumId;
    @Column(name = "user_id")
    @NotNull(message = "User id for album rec is required")
    private long userId;
    @NotNull(message = "Liked for album rec is required")
    private boolean liked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
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
        AlbumRecommendation that = (AlbumRecommendation) o;
        return getId() == that.getId() && getAlbumId() == that.getAlbumId() && getUserId() == that.getUserId() && isLiked() == that.isLiked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAlbumId(), getUserId(), isLiked());
    }

    @Override
    public String toString() {
        return "AlbumRecommendation{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
