package com.movies.moviedata.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long movieId;
    private long ratingId;

    private int actualNumericalRating;

    public int getRating() {
        return actualNumericalRating;
    }

    public void setRating(int rating) {
        this.actualNumericalRating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }
}
