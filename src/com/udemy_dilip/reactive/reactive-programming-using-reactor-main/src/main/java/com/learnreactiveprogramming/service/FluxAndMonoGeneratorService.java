package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {


    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("jenny", "penny", "loko", "glory")); // db or remote server
    }

    public Mono<String> namesMono(){
        return Mono.just("Penny");
    }

    public static void main(String[] args) {
        ;
    }
}
