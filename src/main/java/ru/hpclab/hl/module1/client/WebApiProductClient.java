package ru.hpclab.hl.module1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@Repository
@RequiredArgsConstructor
public class WebApiProductClient {
    private final WebClient coreServiceWebClient;
    private final ObservabilityService observabilityService;

    public Product findProductById(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":findProductById");

        Product product = coreServiceWebClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        observabilityService.stop(getClass().getSimpleName() + ":findProductById");

        return product;
    }
}
