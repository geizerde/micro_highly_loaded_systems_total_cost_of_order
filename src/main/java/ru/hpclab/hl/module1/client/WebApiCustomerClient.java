package ru.hpclab.hl.module1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.model.Customer;

@Repository
@RequiredArgsConstructor
public class WebApiCustomerClient {
    private final WebClient coreServiceWebClient;

    public Customer findCustomerById(Long id) {
        return coreServiceWebClient.get()
                .uri("/customers/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
    }
}