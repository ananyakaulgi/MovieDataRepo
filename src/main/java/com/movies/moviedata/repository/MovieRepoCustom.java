package com.movies.moviedata.repository;

import com.movies.moviedata.model.MovieGenre;
import com.movies.moviedata.model.MovieRating;
import com.movies.moviedata.model.Movies;

import java.util.List;

public interface MovieRepoCustom {

    List<Movies> getAllMoviesByYear(int year);

}
