package ru.itis.lazyMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.itis.lazyMarket.dto.BasketDto;
import ru.itis.lazyMarket.dto.ProdID;
import ru.itis.lazyMarket.dto.ProdList;
import ru.itis.lazyMarket.models.Basket;
import ru.itis.lazyMarket.models.Buyer;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;
import ru.itis.lazyMarket.repositories.BasketsRepository;
import ru.itis.lazyMarket.repositories.BuyersRepository;
import ru.itis.lazyMarket.repositories.ProductsRepository;
import ru.itis.lazyMarket.repositories.SupermarketsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BasketsServiceImpl implements BasketsService {

    @Autowired
    BasketsRepository basketsRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    BuyersRepository buyersRepository;

    @Autowired
    SupermarketsRepository supermarketsRepository;

    private List<Product> products = new ArrayList<>();

    @Override
    public Mono<Void> addAll(ProdList prods) {
        System.out.println(prods.getProductNames().size() + " " + prods.getUserId() + " " + prods.getSupermarketId());

        Basket b = Basket.builder()
                .buyer(buyersRepository.getOne((long) prods.getUserId()))
                .supermarket(supermarketsRepository.getOne((long) prods.getSupermarketId()))
                .build();

        basketsRepository.save(b);

        for (String name: prods.getProductNames()) {
            Product p = productsRepository.findByName(name).get();
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
            p.setBasket(b);
            products.add(p);
        }
        System.out.println("aaaaa " + products.size());
        productsRepository.saveAll(products);

        b.setProducts(products);
        basketsRepository.save(b);
        return Mono.empty();
    }

    @Override
    public Mono<Void> makeOrder(BasketDto basketDto) {
        Buyer buyer = buyersRepository.findByFirstNameAndLastName(basketDto.getFirstName(), basketDto.getLastName()).get();
        Supermarket supermarket = supermarketsRepository.findByTitle(basketDto.getSupermarketName()).get();
        Basket basket = basketsRepository.findByBuyerAndSupermarket(buyer,supermarket).get();
        System.out.println(basket.getId());
        return Mono.empty();
    }
}
