package ru.itis.lazyMarket.service;

import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;

public interface SupermarketService {
    Supermarket publish(Long var1);
    Flux<Supermarket> getAllSupermarkets();
}
