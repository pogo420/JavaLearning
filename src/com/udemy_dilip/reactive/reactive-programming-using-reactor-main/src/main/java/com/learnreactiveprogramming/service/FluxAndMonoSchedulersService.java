package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

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

    private String upperCase(String name) {
        delay(1000);  // blocking code
        return name.toUpperCase();
    }

}
