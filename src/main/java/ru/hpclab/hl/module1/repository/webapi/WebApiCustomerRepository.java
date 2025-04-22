package ru.hpclab.hl.module1.repository.webapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.model.Customer;

@Repository
@RequiredArgsConstructor
public class WebApiCustomerRepository {
    private final WebClient coreServiceWebClient;

    public Customer findCustomerById(Long id) {
        return coreServiceWebClient.get()
                .uri("/customers/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
    }
}