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
}
