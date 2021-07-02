package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxAndMonoSchedulersServiceTest {

    FluxAndMonoSchedulersService fluxAndMonoSchedulersService = new
            FluxAndMonoSchedulersService();

    @Test
    void explore_publishOn() {
        Flux<String> stringFlux = fluxAndMonoSchedulersService.explore_publishOn();

        StepVerifier.create(stringFlux).expectNextCount(6).verifyComplete();
    }
}