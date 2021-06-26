package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceTest {

    private MovieInfoService movieInfoService = new MovieInfoService();
    private ReviewService reviewService = new ReviewService();

    MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewService);

    @Test
    void getAllMovies() {

        Flux<Movie> allMovies = movieReactiveService.getAllMovies();

        StepVerifier.create(allMovies)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                });
    }

    @Test
    void  getMovieById(){
        long movieId=100L;
        Mono<Movie> movieById = movieReactiveService.getMovieById(movieId);

        StepVerifier.create(movieById)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                });

    }
}