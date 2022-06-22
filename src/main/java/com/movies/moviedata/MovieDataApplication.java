package com.movies.moviedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.movies"})
@EnableJpaRepositories("com.movies")
public class MovieDataApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MovieDataApplication.class, args);
    }

}
