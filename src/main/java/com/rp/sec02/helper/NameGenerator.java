package com.rp.sec02.helper;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

//    public static List<String> getNames(int count) {
//        List<String> names = new ArrayList<>(count);
//        for (int i = 0; i < count; i++) {
//            names.add(getname());
//        }
//        return names;
//    }

    public static Flux<String> getNames(int count) {
        return Flux.range(0, count)
                .map(i -> getname());
    }

    private static String getname() {
        System.out.println("Generating name...");
        Util.sleepSeconds(3);
        return Util.faker().name().fullName();
    }
}
