package ru.hpclab.hl.module1.repository.webapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.dto.OrderDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebApiOrderRepository {
    private final WebClient coreServiceWebClient;

    public List<OrderDTO> findAll() {
        return coreServiceWebClient.get()
                .uri("/orders")
                .retrieve()
                .bodyToFlux(OrderDTO.class)
                .collectList()
                .block();
    }
}
