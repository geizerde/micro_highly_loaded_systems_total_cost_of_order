package ru.hpclab.hl.module1.client;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.dto.OrderDTO;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebApiOrderClient {
    private final WebClient coreServiceWebClient;
    private final ObservabilityService observabilityService;

    @Retry(name = "CORE_SERVICE")
    public List<OrderDTO> findAll() {
        observabilityService.start(getClass().getSimpleName() + ":findAll");

        List<OrderDTO> orders = coreServiceWebClient.get()
                .uri("/orders")
                .retrieve()
                .bodyToFlux(OrderDTO.class)
                .collectList()
                .block();

        observabilityService.stop(getClass().getSimpleName() + ":findAll");

        return orders;
    }
}
