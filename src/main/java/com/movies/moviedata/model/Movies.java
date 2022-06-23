package com.movies.moviedata.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@PersistenceContext
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String movieName;
    private Date releaseDate;
    private Double budget;
    private Double revenue;
    private Double runTimeDuration;
    private String originalLanguageId;
    private Long averageRating;
    private Long productionCompanyId;

    public String getMovieName() {
        return movieName;
    }


    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getRunTimeDuration() {
        return runTimeDuration;
    }

    public void setRunTimeDuration(Double runTimeDuration) {
        this.runTimeDuration = runTimeDuration;
    }

    public String getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(String originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Long getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Long averageRating) {
        this.averageRating = averageRating;
    }

    public Long getProductionCompanyId() {
        return productionCompanyId;
    }

    public void setProductionCompanyId(Long productionCompanyId) {
        this.productionCompanyId = productionCompanyId;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
