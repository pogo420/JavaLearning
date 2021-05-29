package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {


    FluxAndMonoGeneratorService testObject = new FluxAndMonoGeneratorService();

    @Test
    void namedFlux(){
        Flux<String> namesFlux=  testObject.namesFlux();

        StepVerifier
                .create(namesFlux)
//                .expectNext("jenny", "penny", "loko", "glory")
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void namedFluxFilter(){
        Flux<String> namesFlux=  testObject.namesFluxFilter(3);
        StepVerifier
                .create(namesFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void namedFluxFilterFm(){
        Flux<String> namesFlux=  testObject.namesFluxFilterFm(3);
        StepVerifier
                .create(namesFlux)
                .expectNextCount(27)
                .verifyComplete();
    }

    @Test
    void namesFluxFilterFmAsync() {
        Flux<String> namesFlux=  testObject.namesFluxFilterFmAsync(3);
        StepVerifier
                .create(namesFlux)
                .expectNextCount(27)
                .verifyComplete();
    }

    @Test
    void namesFluxTransform() {

        Flux<String> namesFlux=  testObject.namesFluxTransform(6);
        StepVerifier
                .create(namesFlux)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFluxTransformSwitchIfEmpty() {
        Flux<String> namesFlux=  testObject.namesFluxTransformSwitchIfEmpty(6);
        StepVerifier
                .create(namesFlux)
                .expectNextCount(9)
                .verifyComplete();
    }
}
