package ru.hpclab.hl.module1.configuration;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hpclab.hl.module1.service.StatisticsService;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class ServicesConfig {
    @Value("${core.service.host}")
    private String coreServiceHost;

    @Value("${core.service.port}")
    private String coreServicePort;

    @Value("${service.statistic.delay:5000}")
    private int delay;

    @Value("${core.service.timeout:60000}")
    private int timeout;

    @Bean
    public WebClient coreServiceWebClient(WebClient.Builder builder) {
        String baseUrl = "http://" + coreServiceHost + ":" + coreServicePort;

        return builder
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .responseTimeout(Duration.ofMillis(timeout))
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)))
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "true")
    StatisticsService statisticsService() {
        return new StatisticsService(delay);
    }
}
