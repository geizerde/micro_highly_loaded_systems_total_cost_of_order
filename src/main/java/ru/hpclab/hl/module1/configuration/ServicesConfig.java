package ru.hpclab.hl.module1.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServicesConfig {
    @Value("${core.service.host}")
    private String coreServiceHost;

    @Value("${core.service.port}")
    private String coreServicePort;

    @Bean
    public WebClient coreServiceWebClient(WebClient.Builder builder) {
        String baseUrl = "http://" + coreServiceHost + ":" + coreServicePort;
        return builder
                .baseUrl(baseUrl)
                .build();
    }
}
