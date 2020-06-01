package ru.itis.lazyMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lazyMarket.models.Buyer;

import java.util.Optional;

public interface BuyersRepository extends JpaRepository<Buyer, Long> {
    Optional<Buyer> findByFirstNameAndLastName(String firstName, String lastName);
}
