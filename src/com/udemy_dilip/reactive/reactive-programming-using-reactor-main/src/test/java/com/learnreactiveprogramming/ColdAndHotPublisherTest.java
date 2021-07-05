package com.learnreactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class ColdAndHotPublisherTest {

    @Test
    void coldPublisherTest(){

        Flux<Integer> range = Flux.range(1, 10);

        range.subscribe(val -> System.out.println("Subscriber 1:"+val));
        range.subscribe(val -> System.out.println("Subscriber 2:"+val));

    }

    @Test
    void hotPublisherTest(){

        Flux<Integer> range = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));


        ConnectableFlux<Integer> publish = range.publish();
        publish.connect();

        publish.subscribe(val -> System.out.println("Subscriber 1:"+val));
        delay(2000);
        publish.subscribe(val -> System.out.println("Subscriber 2:"+val));
        delay(2000);
    }
}
