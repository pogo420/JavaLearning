package com.learnreactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

@Slf4j
public class FluxAndMonoSchedulersService {

    static List<String> namesList = List.of("alex", "ben", "chloe");
    static List<String> namesList1 = List.of("adam", "jill", "jack");
    
    
    public Flux<String> explore_publishOn(){
        Flux<String> namedFlux = Flux.fromIterable(namesList)
                .publishOn(Schedulers.parallel())  // Enforce thread pool
                .map(this::upperCase)
                .log();

        Flux<String> namedFlux1 = Flux.fromIterable(namesList1)
                .publishOn(Schedulers.parallel())
                .map(this::upperCase)
                .map(names -> {
                    System.out.println(names);
                    return names;
                })
                .log();

        return namedFlux.mergeWith(namedFlux1);
    }

    public ParallelFlux<String> explore_parallel(){
        // Getting core count
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        log.info("Available processors: {}", availableProcessors);

        return Flux.fromIterable(namesList)
//                .publishOn(Schedulers.parallel())  // Enforce thread pool
                .parallel()
                .runOn(Schedulers.parallel())
                .map(this::upperCase)
                .log();
    }

    public Flux<String> explore_parallel_with_flatMap(){
        // Getting core count
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        log.info("Available processors: {}", availableProcessors);

        return Flux.fromIterable(namesList)
                .flatMap(
                        name -> {  // another approach for concurrent execution; Flatmap + reactive + subscribeOn
                            return Mono.just(name)
                                    .map(this::upperCase)
                                    .subscribeOn(Schedulers.parallel());
                        }
                )
                .log();
    }

    private String upperCase(String name) {
        delay(1000);  // blocking code
        return name.toUpperCase();
    }

}
