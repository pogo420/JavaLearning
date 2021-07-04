package com.learnreactiveprogramming;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class BackpressureTest {

    @Test
    void testBackPressure(){
        Flux
                .range(0, 1000)
//                .subscribe(num -> log.info("values is: {}", num))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
//                        super.hookOnSubscribe(subscription);
                        subscription.request(10);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
//                        super.hookOnNext(value);
                        log.info("values is: {}", value);

                    }

                    @Override
                    protected void hookOnComplete() {
//                        super.hookOnComplete();
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
//                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
//                        super.hookOnCancel();
                    }
                })
        ;
    }
}
