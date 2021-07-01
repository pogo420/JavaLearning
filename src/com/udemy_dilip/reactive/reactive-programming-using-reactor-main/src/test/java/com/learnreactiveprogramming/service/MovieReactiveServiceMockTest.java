package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.exception.MovieException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MovieReactiveServiceMockTest {

    @Mock
    private MovieInfoService movieInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    MovieReactiveService movieReactiveService;

    @Test
    void getAllMovies() {

        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();  // method stubs

        Mockito.when(reviewService.retrieveReviewsFlux(anyLong()))
                .thenCallRealMethod();

        Flux<Movie> allMovies = movieReactiveService.getAllMovies();

        StepVerifier.create(allMovies).expectNextCount(3).verifyComplete();
    }

    @Test
    void getAllMovies_1() {

        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();  // method stubs

        Mockito.when(reviewService.retrieveReviewsFlux(anyLong()))
                .thenThrow(new MovieException("Ola"));

        Flux<Movie> allMovies = movieReactiveService.getAllMovies();

        StepVerifier
                .create(allMovies)
                .expectError(MovieException.class)
                .verify();
    }

    @Test
    void getAllMovies_retry() {

        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();  // method stubs

        Mockito.when(reviewService.retrieveReviewsFlux(anyLong()))
                .thenThrow(new RuntimeException("Ola"));

        Flux<Movie> allMovies = movieReactiveService.getAllMovies_retry();

        StepVerifier
                .create(allMovies)
                .expectError(RuntimeException.class)
                .verify();

        verify(reviewService, times(4))
                .retrieveReviewsFlux(anyLong());  // checking for 3 times retry
    }

    @Test
    void getAllMovies_retryWhen() {

        Mockito.when(movieInfoService.retrieveMoviesFlux())
                .thenCallRealMethod();  // method stubs

        Mockito.when(reviewService.retrieveReviewsFlux(anyLong()))
                .thenThrow(new RuntimeException("Ola"));

        Flux<Movie> allMovies = movieReactiveService.getAllMovies_retryWhen();

        StepVerifier
                .create(allMovies)
                .expectError(RuntimeException.class)
                .verify();

        verify(reviewService, times(4))
                .retrieveReviewsFlux(anyLong());  // checking for 3 times retry
    }

}