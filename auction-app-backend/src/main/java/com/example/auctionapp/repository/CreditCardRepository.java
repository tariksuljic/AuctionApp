package com.example.auctionapp.repository;

import com.example.auctionapp.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, UUID> {
}
