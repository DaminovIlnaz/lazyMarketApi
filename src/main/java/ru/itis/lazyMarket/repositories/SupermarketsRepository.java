package ru.itis.lazyMarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.lazyMarket.models.Supermarket;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface SupermarketsRepository extends JpaRepository<Supermarket, Long> {
    @RestResource(
            path = "Open",
            rel = "findAllOpen"
    )
    @Query("from Supermarket supermarket where supermarket.state = 'Open'")
    Page<Supermarket> findAllOpen(Pageable var1);

    List<Supermarket> findAll();

    Optional<Supermarket> findByTitle(String title);
}
