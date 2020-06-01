package ru.itis.lazyMarket.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.dto.ProdID;
import ru.itis.lazyMarket.dto.UserRequest;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;
import ru.itis.lazyMarket.repositories.ProductsRepository;
import ru.itis.lazyMarket.repositories.SupermarketsRepository;
import ru.itis.lazyMarket.service.ProductsService;
import ru.itis.lazyMarket.service.SupermarketService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/supermarkets")
public class SupermarketController {

    @Autowired
    private SupermarketService supermarketService;

    @RequestMapping(value = "/getAllSupermarkets",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method = RequestMethod.POST)
    public Flux<Supermarket> getAllSupermarkets(){
        return supermarketService.getAllSupermarkets();
    }
}
