package com.movies.moviedata.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

//@Service("moviesService")
public interface MoviesService {

    public List<BigDecimal> getProdCompanyDetails(Long productionId, int year);
    public String getMostPopularGenre( int year);
}
