package ru.itis.lazyMarket.service;

import reactor.core.publisher.Mono;
import ru.itis.lazyMarket.dto.BasketDto;
import ru.itis.lazyMarket.dto.ProdList;
import ru.itis.lazyMarket.models.Basket;
import ru.itis.lazyMarket.models.Product;

import java.util.List;

public interface BasketsService {
    Mono<Void> addAll(ProdList prods);
    Mono<Void> makeOrder(BasketDto basketDto);
}
