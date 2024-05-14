package com.example.auctionapp.repository;

import com.example.auctionapp.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {
}
