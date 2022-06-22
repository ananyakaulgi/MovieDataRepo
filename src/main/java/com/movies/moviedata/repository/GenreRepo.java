package com.movies.moviedata.repository;

import com.movies.moviedata.model.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo {
    Genre getGenreById(Long genreId);
}
