package ru.itis.lazyMarket.service;

import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.entries.UserFromApi;

public interface ApiDataService {
    Flux<UserFromApi> getAll();
}
