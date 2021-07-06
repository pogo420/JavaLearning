package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceRestClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies/")
            .build();

    private MovieInfoService movieInfoService = new MovieInfoService(webClient);

    private ReviewService reviewService = new ReviewService(webClient);

    private MovieReactiveService movieReactiveService =
            new MovieReactiveService(movieInfoService, reviewService);

    @Test
    void getAllMovies_restCient() {
        Flux<Movie> allMovies_restCient = movieReactiveService.getAllMovies_restCient();
        StepVerifier
                .create(allMovies_restCient)
                .expectNextCount(7)
                .verifyComplete();
    }
}