package com.movies.moviedata.repository;

public interface MovieRatingCustomRepo {
    Double getMovieRatingsByMovieId(long movieId);
}
