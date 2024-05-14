package com.example.auctionapp.repository;

import com.example.auctionapp.entity.PaymentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, UUID> {
}
