package com.movies.moviedata.repository;

import com.movies.moviedata.model.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRatingRep extends JpaRepository<MovieRating, Long>, MovieRatingCustomRepo {

}
