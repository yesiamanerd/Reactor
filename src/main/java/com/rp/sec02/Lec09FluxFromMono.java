package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09FluxFromMono {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("A");

        // create flux from mono
        Flux<String> flux = Flux.from(mono);

        doSomthing(flux);

        // create mono from Flux
        Flux.range(1, 10)
                .filter(i -> i > 3)
                .next()
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    private static void doSomthing(Flux<String> flux) {
        flux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

}
