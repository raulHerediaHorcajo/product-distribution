package com.productdistribution.backend.schedulers;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.productdistribution.backend.services.DistributionService;
import com.productdistribution.backend.utils.JsonChangeWatcher;

@Component
public class DistributionScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DistributionScheduler.class);

    private final DistributionService distributionService;
    private final JsonChangeWatcher jsonChangeWatcher;

    public DistributionScheduler(DistributionService distributionService, JsonChangeWatcher jsonChangeWatcher) {
        this.distributionService = distributionService;
        this.jsonChangeWatcher = jsonChangeWatcher;
    }

    @PostConstruct
    public void onStartup() {
        logger.info("🔁 Executing initial distribution on application startup...");
        distributionService.distributeProducts();
        jsonChangeWatcher.updateLastModifiedDates();
    }

    @Scheduled(cron = "${scheduler.distribution.cron}")
    public void nightlyDistribution() {
        logger.info("🔍 Checking if JSON files have changed...");

        if (jsonChangeWatcher.hasAnyChanged()) {
            logger.info("🔁 Changes detected in JSON files. Executing nightly redistribution...");
            distributionService.distributeProducts();
        } else {
            logger.info("⏭️  No changes detected in files. Distribution skipped.");
        }
    }
}