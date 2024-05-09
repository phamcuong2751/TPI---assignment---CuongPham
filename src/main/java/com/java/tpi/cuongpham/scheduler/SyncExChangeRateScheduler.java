package com.java.tpi.cuongpham.scheduler;

import com.java.tpi.cuongpham.service.SyncExChangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SyncExChangeRateScheduler {
    private final SyncExChangeRateService syncExChangeRateService;

    /**
     * Scheduler start sync exchange rate every 10s
     */
    @Scheduled(fixedDelay = 10000)
    public void SyncExChangeRateTask() {
        syncExChangeRateService.execution();
    }
}
