package ru.itis.lazyMarket.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.lazyMarket.entries.ApiDataRecord;
import ru.itis.lazyMarket.entries.UserFromApi;

import java.util.Arrays;

@Component
public class ApiWebClient implements ApiClient {

    private WebClient client;

    public ApiWebClient(@Value("${comments.url}") String url) {
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<UserFromApi> getAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(ApiDataRecord[].class))
                .flatMapIterable(Arrays::asList)
                .map(record ->
                        UserFromApi.builder()
                                .id(record.getId())
                                .name(record.getName())
                                .username(record.getUsername())
                                .email(record.getEmail())
                                .address(record.getAddress())
                                .phone(record.getPhone())
                                .website(record.getWebsite())
                                .company(record.getCompany())
                                .build());
    }
}
