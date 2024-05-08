package com.example.auctionapp.repository;

import com.example.auctionapp.entity.BidEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BidRepository extends JpaRepository<BidEntity, UUID> {
    BidEntity findTopBidEntityByProductEntity_ProductIdOrderByBidTimeDesc(final UUID productId);
    Page<BidEntity> findBidEntitiesByUserEntity_UserIdOrderByBidTimeDesc(final UUID userId, final Pageable pageable);
}
