package com.movies.moviedata.service.impl;

import com.movies.moviedata.model.Genre;
import com.movies.moviedata.model.MovieGenre;
import com.movies.moviedata.model.Movies;
import com.movies.moviedata.repository.MovieGenreRepo;
import com.movies.moviedata.repository.MovieRatingRep;
import com.movies.moviedata.repository.MovieRepository;
import com.movies.moviedata.service.MoviesService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("moviesService")
@Data
public class MoviesServiceImpl implements MoviesService {


    private MovieRepository repository ;
    private MovieGenreRepo genreRepository ;
    private MovieRatingRep ratingRepository ;

    public MovieRepository getRepository() {
        return repository;
    }

    @Autowired
    public void setRepository(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieGenreRepo getGenreRepository() {
        return genreRepository;
    }

    @Autowired
    public void setGenreRepository(MovieGenreRepo genreRepository) {
        this.genreRepository = genreRepository;
    }

    public MovieRatingRep getRatingRepository() {
        return ratingRepository;
    }

    @Autowired
    public void setRatingRepository(MovieRatingRep ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Autowired
    public MoviesServiceImpl(MovieRepository repository, MovieGenreRepo genreRepository, MovieRatingRep ratingRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
        this.ratingRepository = ratingRepository;
    }



    public List<BigDecimal> getProdCompanyDetails(Long productionId, int year){
        List<Movies> movieList = repository.getAllMoviesByYear(year);
        Double budget = 0.0, revenue = 0.0;

        for(Movies movie: movieList){
            if(movie.getProductionCompanyId() == productionId){
                budget += movie.getBudget();
                revenue += movie.getRevenue();
            }
        }

        return Arrays.asList(BigDecimal.valueOf(budget), BigDecimal.valueOf(revenue));
    }

    public String getMostPopularGenre( int year){

        //Get a List of Movies for the Year
        //Pull a Map of MovieId: AVG(Rating)
        //For every MovieId, fetch List of Genres
        //Add the Genre to a Hashmap and Aggregate the Ratings across Multiple Movies for a Given Genre
        //Find the MAX Rating in the entire Hashmap values and return the associated GENRE to it.


        StringBuilder mostPopularGenre = new StringBuilder();
        List<Movies> movieList = repository.getAllMoviesByYear(year);
        List<MovieGenre> genresOfMovie = new ArrayList<MovieGenre>();
        HashMap<Genre, Double> genreRatings = new HashMap<Genre, Double>();
        HashMap<Long, Double> movieRating = calculateMovieRatings(movieList);
        for(Long movieId: movieRating.keySet()){
            genresOfMovie = genreRepository.getGenresByMovieId(movieId);
            for(MovieGenre genres : genresOfMovie){
                Genre currentGenre = genreRepository.getGenreById(genres.getGenreId());
                if(!genreRatings.containsKey(currentGenre)){
                    genreRatings.put(currentGenre,0.0);
                }else{
                    Double currentRating = genreRatings.get(currentGenre);
                    currentRating += movieRating.get(movieId);
                    genreRatings.put(currentGenre, currentRating);
                }
            }

        }
        Double maxRating = 0.0;
        for(Genre g: genreRatings.keySet()){
            if(genreRatings.get(g)>maxRating){
                maxRating = genreRatings.get(g);
                mostPopularGenre = new StringBuilder(g.getName());
            }
        }

        return mostPopularGenre.toString();
    }

    private HashMap<Long, Double> calculateMovieRatings(List<Movies> movies){
        Double rating = 0.0;
        HashMap<Long, Double> result = new HashMap<Long, Double>();
        for(Movies movie: movies){
            rating = ratingRepository.getMovieRatingsByMovieId(movie.getId());
            if(!result.containsKey(movie.getId())){
                result.put(movie.getId(), rating);
            }
        }
        return result;
    }

}
