package com.example.auctionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AuctionAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionAppBackendApplication.class, args);
    }
}
