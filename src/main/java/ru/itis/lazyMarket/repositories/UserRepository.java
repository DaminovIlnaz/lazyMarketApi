package ru.itis.lazyMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lazyMarket.models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    List<User> findAll();

}
