package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.test.StepVerifier;

class FluxAndMonoSchedulersServiceTest {

    FluxAndMonoSchedulersService fluxAndMonoSchedulersService = new
            FluxAndMonoSchedulersService();

    @Test
    void explore_publishOn() {
        Flux<String> stringFlux = fluxAndMonoSchedulersService.explore_publishOn();

        StepVerifier.create(stringFlux).expectNextCount(6).verifyComplete();
    }

    @Test
    void explore_parallel() {

        ParallelFlux<String> stringFlux = fluxAndMonoSchedulersService.explore_parallel();

        StepVerifier.create(stringFlux).expectNextCount(3).verifyComplete();
    }

    @Test
    void explore_parallel_with_flatMap() {
        Flux<String> stringFlux = fluxAndMonoSchedulersService.explore_parallel_with_flatMap();

        StepVerifier.create(stringFlux).expectNextCount(3).verifyComplete();
    }
}