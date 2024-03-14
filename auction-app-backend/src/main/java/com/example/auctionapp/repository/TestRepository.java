package com.example.auctionapp.repository;

import com.example.auctionapp.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {
    // Nothing to do here
}
