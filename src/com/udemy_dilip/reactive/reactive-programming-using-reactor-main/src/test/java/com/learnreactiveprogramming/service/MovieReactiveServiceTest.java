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
    private RevenueService revenueService = new RevenueService();

    MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewService, revenueService);

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

    @Test
    void getMovieById_withRevenue() {
        long movieId=100L;
        Mono<Movie> movieById_withRevenue = movieReactiveService.getMovieById_withRevenue(movieId);

        StepVerifier
                .create(movieById_withRevenue)
                .assertNext(
                movie -> {
                    assertNotNull(movie.getRevenue());
                }
        ).verifyComplete();
    }
}