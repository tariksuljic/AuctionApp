package com.example.auctionapp.util;

import com.example.auctionapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ProductTaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ProductTaskScheduler.class);

    private final ProductRepository productRepository;

    public ProductTaskScheduler(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *", zone = "UTC")
    public void deactivateExpiredProducts() {
        final LocalDateTime now = LocalDateTime.now();

        final int updatedCount = this.productRepository.updateProductStatus(now);

        logger.info("Deactivated {} products that expired as of {}", updatedCount, now);
    }
}
