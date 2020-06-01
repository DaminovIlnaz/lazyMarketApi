package ru.itis.lazyMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;
import ru.itis.lazyMarket.repositories.ProductsRepository;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Flux<Product> getAllProdBySupermarketId(Supermarket supermarket) {
        Flux<Product> flux =  Flux.fromIterable(productsRepository.getProductBySupermarket(supermarket));
        return flux;    }
}
