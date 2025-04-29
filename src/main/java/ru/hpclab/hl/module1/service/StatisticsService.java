package ru.hpclab.hl.module1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import ru.hpclab.hl.module1.service.cache.CustomerRedisCache;

public class StatisticsService {
    private final int delay;

    private final CustomerRedisCache customerCache;

    public StatisticsService(int delay, CustomerRedisCache customerCache) {
        this.delay = delay;
        this.customerCache = customerCache;
    }

    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedRateString = "${service.statistic.delay}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " - Fixed rate task async - " + delay + " - customer count: "
                        + customerCache.size());
    }
}
