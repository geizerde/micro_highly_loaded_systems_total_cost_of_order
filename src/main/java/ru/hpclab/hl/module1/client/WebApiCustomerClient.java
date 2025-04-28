package ru.hpclab.hl.module1.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.model.Product;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebApiCustomerClient {
    private final WebClient coreServiceWebClient;
    private final ObservabilityService observabilityService;

    @CircuitBreaker(name = "CORE_SERVICE", fallbackMethod = "fallback")
    @Retry(name = "CORE_SERVICE")
    public Customer findCustomerById(Long id) {
        System.out.println("findCustomerById");
        observabilityService.start(getClass().getSimpleName() + ":findCustomerById");

        Customer customer = coreServiceWebClient.get()
                .uri("/customers/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

        observabilityService.stop(getClass().getSimpleName() + ":findCustomerById");

        return customer;
    }

    private Customer fallback(Long id, Exception e) {
        System.out.println("Fallback for findProductById triggered: " + e.getMessage());
        return null;
    }
}
