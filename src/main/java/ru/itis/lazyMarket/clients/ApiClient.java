package ru.itis.lazyMarket.clients;

import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.entries.UserFromApi;

public interface ApiClient {
    Flux<UserFromApi> getAll();
}
