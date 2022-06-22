package com.movies.moviedata.repository.impl;

import com.movies.moviedata.model.MovieGenre;
import com.movies.moviedata.model.MovieRating;
import com.movies.moviedata.model.Movies;
import com.movies.moviedata.repository.MovieRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class MovieRepoCustomImpl implements MovieRepoCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movies> getAllMoviesByYear(int year) {
        Query query = entityManager.createNativeQuery("Select m.* from movies m where YEAR(release_date) = "+year, Movies.class);
        return query.getResultList();
    }




}
