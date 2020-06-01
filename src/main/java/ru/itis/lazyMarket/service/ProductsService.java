package ru.itis.lazyMarket.service;

import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;

import java.util.List;

public interface ProductsService {
    Flux<Product> getAllProdBySupermarketId(Supermarket supermarket);
}
