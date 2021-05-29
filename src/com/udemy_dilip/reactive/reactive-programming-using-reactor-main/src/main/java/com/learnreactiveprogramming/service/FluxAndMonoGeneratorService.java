package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {


    public Flux<String> namesFlux(){
        // simple flux
        return Flux
                .fromIterable(List.of("jenny", "penny", "loko", "glory"))
                .map(String::toUpperCase)
                .log(); // db or remote server
    }

    public Flux<String> namesFluxFilter(int stringLength){
        // simple flux with filter and map
        return Flux
                .fromIterable(List.of("jenny", "penny", "loko", "glory"))
                .map(String::toUpperCase)
                .filter(name -> name.length() > stringLength);
    }

    private Flux<String> splitString(String value){
        // util for splitting a string
        return Flux.fromArray(value.split(""));
    }

    private Flux<String> splitStringWithDelay(String value){
        // Flux to replicate async operations
        return Flux
                .fromArray(value.split(""))
                .delayElements(Duration
                        .ofMillis(new Random().nextInt(1000)));
    }

    public Flux<String> namesFluxFilterFm(int stringLength){
        // Flux to replicate async operations
        return Flux
                .fromIterable(List.of("jenny", "penny", "loko", "glory"))
                .map(String::toUpperCase)
                .filter(name -> name.length() > stringLength)
                .map(name -> name.length()+"-"+name)
                .flatMap(this::splitString);
    }

    public Flux<String> namesFluxFilterFmAsync(int stringLength){
        // Flux to replicate async operations
        return Flux
                .fromIterable(List.of("jenny", "penny", "loko", "glory"))
                .map(String::toUpperCase)
                .filter(name -> name.length() > stringLength)
                .map(name -> name.length()+"-"+name)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    public Mono<String> namesMono(){
        return Mono.just("Penny");
    }

    public Mono<String> namesMonoFlatMap(){
        // Mono with flat map
        return Mono.just("Penny")
                .flatMap(Mono::just)
                .log();
    }

    public Flux<String> namesMonoFlatMapMany(){
        // Mono with flat map many: When we need to return FLux in mono pipeline
        return Mono.just("Penny")
                .flatMapMany(this::splitString)
                .log();
    }

    public Flux<String> namesFluxTransform(int stringLength){
        // Flux transform

        Function<Flux<String>, Flux<String>> filterFunction = data -> {
            return data.map(String::toUpperCase).filter(name -> name.length() > stringLength);
        };

        return Flux
                .fromIterable(List.of("jenny", "penny", "loko", "glory"))
                .transform(filterFunction)
                .map(name -> name.length()+"-"+name)
                .flatMap(this::splitString)
                .defaultIfEmpty("default")
                ;
    }

    public Flux<String> namesFluxTransformSwitchIfEmpty(int stringLength){
        // Flux transform

        Function<Flux<String>, Flux<String>> filterFunction = data -> {
            return data
                    .map(String::toUpperCase)
                    .filter(name -> name.length() > stringLength)
                    .map(name -> name.length()+"-"+name)
                    .flatMap(this::splitString);
        };

        Flux<String> aDefault = Flux.just("default").transform(filterFunction);

        return Flux
                .fromIterable(List.of("jenny", "penny", "loko", "glory"))
                .transform(filterFunction)
                .switchIfEmpty(aDefault);
    }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
//        fluxAndMonoGeneratorService.namesFlux().subscribe(System.out::println);
//        fluxAndMonoGeneratorService.namesMono().subscribe(System.out::println);
//        fluxAndMonoGeneratorService.namesFluxFilterFm(3).subscribe(System.out::println);
//        fluxAndMonoGeneratorService.namesFluxFilterFmAsync(3).subscribe(System.out::println);
//        fluxAndMonoGeneratorService.namesMonoFlatMap().subscribe(System.out::println);
//        fluxAndMonoGeneratorService.namesMonoFlatMapMany().subscribe(System.out::println);

        fluxAndMonoGeneratorService.namesFluxTransform(3).subscribe(System.out::println);
    }
}
