package ru.itis.lazyMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.itis.lazyMarket.models.User;

import java.util.List;

public interface TestRepo extends JpaRepository<User, Long> {
    List<User> findAll();
}
