package com.rp.sec01;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {
    public static void main(String[] args) {
        userRepository(1)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );
    }

    private static Mono<String> userRepository(int userId){

        if(userId == 1) {
            return Mono.just(Util.faker().name().firstName());
        } else if(userId == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not in the allowed range"));
        }
    }
}
