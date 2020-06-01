package ru.itis.lazyMarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello");
    }

    @GetMapping("/test")
    public Mono<String> test() {
        return Mono.just("test");
    }
}
