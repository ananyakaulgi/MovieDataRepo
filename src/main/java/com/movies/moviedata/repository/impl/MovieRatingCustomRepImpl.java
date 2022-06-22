package com.movies.moviedata.repository.impl;

import com.movies.moviedata.model.Movies;
import com.movies.moviedata.repository.MovieRatingCustomRepo;
import com.movies.moviedata.repository.MovieRatingRep;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MovieRatingCustomRepImpl implements MovieRatingCustomRepo {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Double getMovieRatingsByMovieId(long movieId) {
        Query query = entityManager.createNativeQuery("Select AVG(actualNumericalRating) from MovieRating where movieId =  "+movieId, Double.class);
        return (Double) query.getSingleResult();
    }
}
