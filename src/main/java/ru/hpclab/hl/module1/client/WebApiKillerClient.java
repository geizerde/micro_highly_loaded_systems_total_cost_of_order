package ru.hpclab.hl.module1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WebApiKillerClient {

    private final WebClient coreServiceWebClient;

    public void crashCoreService() {
        coreServiceWebClient.post()
                .uri("/internal/crash")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
