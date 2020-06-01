package ru.itis.lazyMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lazyMarket.models.Basket;
import ru.itis.lazyMarket.models.Buyer;
import ru.itis.lazyMarket.models.Supermarket;

import java.util.Optional;

public interface BasketsRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByBuyerAndSupermarket(Buyer buyer, Supermarket supermarket);
}
