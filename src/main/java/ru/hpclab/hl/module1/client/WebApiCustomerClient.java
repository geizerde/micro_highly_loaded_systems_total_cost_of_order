package ru.hpclab.hl.module1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.model.Customer;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@Repository
@RequiredArgsConstructor
public class WebApiCustomerClient {
    private final WebClient coreServiceWebClient;
    private final ObservabilityService observabilityService;

    public Customer findCustomerById(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":findCustomerById");

        Customer customer = coreServiceWebClient.get()
                .uri("/customers/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

        observabilityService.stop(getClass().getSimpleName() + ":findCustomerById");

        return customer;
    }
}
