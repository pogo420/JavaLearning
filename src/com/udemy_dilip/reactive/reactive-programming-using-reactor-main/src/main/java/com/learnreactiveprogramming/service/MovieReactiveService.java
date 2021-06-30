package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.domain.MovieInfo;
import com.learnreactiveprogramming.domain.Review;
import com.learnreactiveprogramming.exception.MovieException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class MovieReactiveService {

    private MovieInfoService movieInfoService;
    private ReviewService reviewService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Movie> getAllMovies(){
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

    public Mono<Movie> getMovieById(long movieId){
        Mono<MovieInfo> movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        Mono<List<Review>> reviewFlux = reviewService.retrieveReviewsFlux(movieId).collectList();

        return movieInfoMono.zipWith(reviewFlux, (movieInfo, review) -> new Movie(movieInfo, review));
    }
}
