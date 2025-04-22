package ru.hpclab.hl.module1.repository.webapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.model.Product;

@Repository
@RequiredArgsConstructor
public class WebApiProductRepository {
    private final WebClient coreServiceWebClient;

    public Product findProductById(Long id) {
        return coreServiceWebClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}
