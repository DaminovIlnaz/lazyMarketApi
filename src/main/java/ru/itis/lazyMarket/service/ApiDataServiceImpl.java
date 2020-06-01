package ru.itis.lazyMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.lazyMarket.clients.ApiClient;
import ru.itis.lazyMarket.entries.UserFromApi;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiDataServiceImpl implements ApiDataService {

    @Autowired
    private List<ApiClient> clients;

    @Override
    public Flux<UserFromApi> getAll() {
        List<Flux<UserFromApi>> fluxes =  clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    private Flux<UserFromApi> getAll(ApiClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}
