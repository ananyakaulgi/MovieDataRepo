package com.movies.moviedata.repository;

import com.movies.moviedata.model.Genre;
import com.movies.moviedata.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieGenreRepo extends JpaRepository<MovieGenre,Long>, GenreRepo {
    List<MovieGenre> getGenresByMovieId(long movieId);

}
