package ru.itis.lazyMarket.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.lazyMarket.dto.BasketDto;
import ru.itis.lazyMarket.dto.ProdList;
import ru.itis.lazyMarket.dto.UserRequest;
import ru.itis.lazyMarket.models.Supermarket;
import ru.itis.lazyMarket.service.BasketsService;

@RestController
@AllArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    BasketsService basketsService;

    @RequestMapping(value = "/addProducts",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method = RequestMethod.POST)
    public Mono<Void> getAllSupermarkets(@RequestBody ProdList prods){
        basketsService.addAll(prods);
        return Mono.empty();
    }

}
