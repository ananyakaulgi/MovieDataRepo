package com.movies.moviedata.repository;

import com.movies.moviedata.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long>, MovieRepoCustom {

    Movies findByMovieName(String name);


}