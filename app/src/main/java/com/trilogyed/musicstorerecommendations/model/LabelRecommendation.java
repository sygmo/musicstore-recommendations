package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "label_recommendation")
public class LabelRecommendation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_recommendation_id")
    private long id;
    @Column(name = "label_id")
    @NotNull(message = "Label id is required")
    private long labelId;
    @Column(name = "user_id")
    @NotNull(message = "User id for label rec is required")
    private long userId;
    @NotNull(message = "Liked for label rec is required")
    private boolean liked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
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
        LabelRecommendation that = (LabelRecommendation) o;
        return getId() == that.getId() && getLabelId() == that.getLabelId() && getUserId() == that.getUserId() && isLiked() == that.isLiked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLabelId(), getUserId(), isLiked());
    }

    @Override
    public String toString() {
        return "LabelRecommendation{" +
                "id=" + id +
                ", labelId=" + labelId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
