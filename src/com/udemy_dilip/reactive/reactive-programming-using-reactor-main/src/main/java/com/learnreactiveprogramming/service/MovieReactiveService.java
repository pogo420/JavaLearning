package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.domain.MovieInfo;
import com.learnreactiveprogramming.domain.Revenue;
import com.learnreactiveprogramming.domain.Review;
import com.learnreactiveprogramming.exception.MovieException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MovieReactiveService {

    private MovieInfoService movieInfoService;
    private ReviewService reviewService;
    private RevenueService revenueService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService, RevenueService revenueService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
        this.revenueService = revenueService;
    }

    public Flux<Movie> getAllMovies() {
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFlux();

        return movieInfoFlux.flatMap(movieInfo -> {
            Mono<List<Review>> listMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
            return listMono.map( reviews -> new Movie(movieInfo, reviews));
        }).onErrorMap(ex->{
            log.error("Exception is", ex);
            throw new MovieException(ex.getMessage());
            }
        );
    }

    public Flux<Movie> getAllMovies_restCient() {
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveAllMovieInfo_restCLient();

        return movieInfoFlux.flatMap(movieInfo -> {
            Mono<List<Review>> listMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
            return listMono.map( reviews -> new Movie(movieInfo, reviews));
        }).onErrorMap(ex->{
                    log.error("Exception is", ex);
                    throw new MovieException(ex.getMessage());
                }
        );
    }


    public Flux<Movie> getAllMovies_retry() {
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFlux();

        return movieInfoFlux.flatMap(movieInfo -> {
            Mono<List<Review>> listMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
            return listMono.map( reviews -> new Movie(movieInfo, reviews));
        }).onErrorMap(ex->{
                    log.error("Exception is", ex);
                    throw new MovieException(ex.getMessage());
                }
        ).retry(3);
    }


    public Flux<Movie> getAllMovies_retryWhen() {

        RetryBackoffSpec retrySpec = Retry.backoff(3, Duration.ofMillis(500)).filter(ex -> {
            return ex instanceof MovieException;
        });

        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFlux();

        return movieInfoFlux.flatMap(movieInfo -> {
            Mono<List<Review>> listMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
            return listMono.map( reviews -> new Movie(movieInfo, reviews));
        }).onErrorMap(ex->{
                    log.error("Exception is", ex);
                    throw new MovieException(ex.getMessage());
                }
        ).retryWhen(retrySpec);
    }

    public Mono<Movie> getMovieById(long movieId) {
        Mono<MovieInfo> movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        Mono<List<Review>> reviewFlux = reviewService.retrieveReviewsFlux(movieId).collectList();

        return movieInfoMono.zipWith(reviewFlux, (movieInfo, review) -> new Movie(movieInfo, review));
    }

    public Mono<Movie> getMovieById_withRevenue(long movieId) {
        Mono<MovieInfo> movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        Mono<List<Review>> reviewFlux = reviewService.retrieveReviewsFlux(movieId).collectList();

        Mono<Revenue> revenueMono = Mono.fromCallable(
                () -> {
                    return revenueService.getRevenue(movieId);
                }
        ).subscribeOn(Schedulers.boundedElastic());

        return movieInfoMono
                .zipWith(reviewFlux, Movie::new)
                .zipWith(revenueMono, (movie, revenue) -> {
                    movie.setRevenue(revenue);
                    return movie;
                });
    }

}
