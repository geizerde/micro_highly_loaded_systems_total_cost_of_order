package ru.hpclab.hl.module1.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebApiProductClient {
    private final WebClient coreServiceWebClient;
    private final ObservabilityService observabilityService;

    @CircuitBreaker(name = "CORE_SERVICE", fallbackMethod = "fallback")
    @Retry(name = "CORE_SERVICE")
    public Product findProductById(Long id) {
        System.out.println("findProductById");
        observabilityService.start(getClass().getSimpleName() + ":findProductById");

        Product product = coreServiceWebClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        observabilityService.stop(getClass().getSimpleName() + ":findProductById");

        return product;
    }

    private Product fallback(Long id, Exception e) {
        System.out.println("Fallback for findProductById triggered: " + e.getMessage());
        return null;
    }
}
