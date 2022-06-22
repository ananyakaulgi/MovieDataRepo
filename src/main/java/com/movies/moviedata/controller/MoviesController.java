package com.movies.moviedata.controller;

import com.movies.moviedata.service.MoviesService;
import com.movies.moviedata.service.impl.MoviesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="api/v1/movies")
public class MoviesController {

    public MoviesService getMovieService() {
        return movieService;
    }

    @Autowired
    @Qualifier("moviesService")
    public void setMovieService(MoviesService movieService) {
        this.movieService = movieService;
    }

    private MoviesService movieService;

    public MoviesController(MoviesService movieService) {
        this.movieService = movieService;
    }



    @RequestMapping(path="/getProductionFinances/{productionId}/{year}",method = RequestMethod.GET)
    public ResponseEntity<?> getProductionFinances(@PathVariable Long productionId, @PathVariable int year) {
        return ResponseEntity.ok().body(movieService.getProdCompanyDetails(productionId, year));
    }

    @RequestMapping(path="/getPopularGenre/{year}", method = RequestMethod.GET)
    public ResponseEntity<?> getPopularGenre(@PathVariable int year) {

        try {
            return ResponseEntity.ok().body(movieService.getMostPopularGenre(year));
            //return ResponseEntity.ok().body("Get popular Movies for " + year);
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Currently Data cannot be found.");

        }
    }
}

