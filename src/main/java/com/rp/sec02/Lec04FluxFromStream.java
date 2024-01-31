package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {


        List<Integer> list = List.of(1, 2, 3,  4, 5);
        Stream<Integer> stream = list.stream();


        Flux<Integer> integerFlux = Flux.fromStream(stream);  // will produce error on second subscription

        Flux<Integer> integerFlux2 = Flux.fromStream(() -> list.stream());


        integerFlux2.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        integerFlux2.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );


    }
}
