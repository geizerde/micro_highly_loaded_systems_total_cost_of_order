package ru.hpclab.hl.module1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import ru.hpclab.hl.module1.service.cache.CustomerCache;

public class StatisticsService {
    private final int delay;

    public StatisticsService(int delay) {
        this.delay = delay;
    }

    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedRateString = "${service.statistic.delay}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " - Fixed rate task async - " + delay + " - customer count: "
                        + CustomerCache.size());
    }
}
