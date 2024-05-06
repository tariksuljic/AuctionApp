package com.example.auctionapp.repository;

import com.example.auctionapp.entity.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BidRepository extends JpaRepository<BidEntity, UUID> {
    BidEntity findTopBidEntityByProductEntity_ProductIdOrderByBidTimeDesc(final UUID productId);
}
