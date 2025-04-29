package ru.hpclab.hl.module1.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.client.WebApiKillerClient;

@Component
@RequiredArgsConstructor
public class CoreCrashScheduler {

    private final WebApiKillerClient webApiKillerClient;

    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedDelayString = "${service.scheduler.core.crash.delay}")
    public void callCrashEndpoint() {
        try {
            webApiKillerClient.crashCoreService();
            System.out.println("Crash request sent to Core Service");
        } catch (Exception e) {
            System.err.println("Failed to call crash endpoint: " + e.getMessage());
        }
    }
}
