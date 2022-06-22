package com.movies.moviedata.controller;

import com.movies.moviedata.model.Movies;
import com.movies.moviedata.service.impl.MoviesServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class MoviesControllerTest {

    @InjectMocks
    MoviesServiceImpl moviesServiceImpl;

    @Mock
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        moviesServiceImpl = Mockito.mock(MoviesServiceImpl.class);
        entityManager = Mockito.mock(EntityManager.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getProductionFinances() throws Exception {

        Movies movie1 = new Movies();
        movie1.setBudget(Double.valueOf(20000));
        movie1.setRevenue(Double.valueOf(3500000));
        movie1.setId(100L);
        movie1.setProductionCompanyId(10L);
        Date date = new Date();
        movie1.setReleaseDate(date);
        Movies movie2 = new Movies();
        movie2.setBudget(Double.valueOf(120000));
        movie2.setRevenue(Double.valueOf(53500000));
        movie2.setId(101L);
        movie2.setProductionCompanyId(20L);
        movie2.setReleaseDate(date);
        List<Movies> allMovies = new ArrayList<>();
        allMovies.add(movie1);
        allMovies.add(movie2);

        List<BigDecimal> expect = (List<BigDecimal>) Arrays.asList(BigDecimal.valueOf(movie1.getBudget()), BigDecimal.valueOf(movie1.getRevenue()));

        when(moviesServiceImpl.getProdCompanyDetails(10L,2009)).thenReturn(expect);

        List<BigDecimal> actual = moviesServiceImpl.getProdCompanyDetails(10L, 2009);
        assertEquals(expect,actual);
        verify(moviesServiceImpl,times(1));
    }

    @Test
    void getPopularGenre() {

        String expect = "Test";
        when(moviesServiceImpl.getMostPopularGenre(2009)).thenReturn(expect);

        String actual = moviesServiceImpl.getMostPopularGenre(2009);
        assertEquals(expect,actual);
        verify(moviesServiceImpl).getMostPopularGenre(2009);
    }
}