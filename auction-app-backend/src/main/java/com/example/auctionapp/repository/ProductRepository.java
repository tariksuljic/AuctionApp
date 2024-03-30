package com.example.auctionapp.repository;

import com.example.auctionapp.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "SELECT p FROM ProductEntity p ORDER BY RANDOM() LIMIT 1")
    Optional<ProductEntity> findRandomProduct();
}
