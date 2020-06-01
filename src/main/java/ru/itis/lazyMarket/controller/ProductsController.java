package ru.itis.lazyMarket.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.dto.ProdID;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.repositories.ProductsRepository;
import ru.itis.lazyMarket.repositories.SupermarketsRepository;
import ru.itis.lazyMarket.service.ApiDataService;
import ru.itis.lazyMarket.service.ProductsService;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    SupermarketsRepository supermarketsRepository;


    @RequestMapping(value = "/getProducts",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            method = RequestMethod.POST)
    public Flux<Product> getProducts(@RequestBody ProdID id){
        return productsService.getAllProdBySupermarketId(supermarketsRepository.getOne((long) id.getId()));
    }
}
