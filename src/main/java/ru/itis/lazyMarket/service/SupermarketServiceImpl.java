package ru.itis.lazyMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.models.Supermarket;
import ru.itis.lazyMarket.repositories.SupermarketsRepository;

@Service
public class SupermarketServiceImpl implements SupermarketService {
    @Autowired
    public SupermarketsRepository supermarketsRepository;

    public SupermarketServiceImpl() {
    }

    public Supermarket publish(Long supermarketId) {
        Supermarket supermarket = (Supermarket)this.supermarketsRepository.findById(supermarketId).orElseThrow(IllegalArgumentException::new);
        supermarket.publish();
        this.supermarketsRepository.save(supermarket);
        return supermarket;
    }

    public Flux<Supermarket> getAllSupermarkets(){
        Flux<Supermarket> flux =  Flux.fromIterable(supermarketsRepository.findAll());
        return flux;
    }

}
