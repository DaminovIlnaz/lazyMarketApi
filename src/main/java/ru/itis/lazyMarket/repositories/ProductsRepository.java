package ru.itis.lazyMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lazyMarket.models.Product;
import ru.itis.lazyMarket.models.Supermarket;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> getProductBySupermarket(Supermarket supermarket);
    Optional<Product> findByName(String name);
}
