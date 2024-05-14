package com.example.auctionapp.service.implementation;

import com.example.auctionapp.entity.BidEntity;
import com.example.auctionapp.entity.ProductEntity;
import com.example.auctionapp.entity.ProductImageEntity;
import com.example.auctionapp.entity.enums.NotificationType;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.model.Bid;
import com.example.auctionapp.repository.BidRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
import com.example.auctionapp.request.BidRequest;
import com.example.auctionapp.response.ProductBidDetailsResponse;
import com.example.auctionapp.service.BidService;
import com.example.auctionapp.service.NotificationService;
import com.example.auctionapp.util.ValidationUtility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final NotificationService notificationService;

    public BidServiceImpl(final BidRepository bidRepository,
                          final UserRepository userRepository,
                          final ProductRepository productRepository,
                          final NotificationService notificationService) {
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    @Override
    public Bid placeBid(final BidRequest bidRequest) {
        BidEntity bidEntity = bidRequest.toEntity();

        if (bidRequest.getProductId() != null) {
            final ProductEntity product = productRepository.findById(bidRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with the given ID does not exist"));

            ValidationUtility.validateBidTime(bidRequest.getBidTime(), product);
            ValidationUtility.validateBidAmount(bidRequest.getBidAmount(), product);
            ValidationUtility.productOwner(bidRequest.getUserId(), product.getUserEntity().getUserId());

            bidEntity.setProduct(product);
        }

        if (bidRequest.getUserId() != null) {
            bidEntity.setUser(userRepository.findById(bidRequest.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with the given ID does not exist")));
        }

        // retrieve the current highest bid entity
        final BidEntity highestBidEntity = bidRepository
                .findTopBidEntityByProductEntity_ProductIdOrderByBidTimeDesc(bidRequest.getProductId());
        final BigDecimal highestBidAmount = highestBidEntity != null ? highestBidEntity.getBidAmount() : null;

        if (highestBidAmount == null || bidRequest.getBidAmount().compareTo(highestBidAmount) > 0) {
            // update the previous highest bidder
            if (highestBidEntity != null) {
                notificationService.notifyUser(highestBidEntity.getUser().getUserId(), NotificationType.LOWER_BID,
                        bidEntity.getProduct().getProductId());
            }

            bidRepository.save(bidEntity); // save the highest bid
            notificationService.notifyUser(bidRequest.getUserId(), NotificationType.HIGHEST_BID,
                    bidEntity.getProduct().getProductId());
        } else {
            notificationService.notifyUser(bidRequest.getUserId(), NotificationType.LOWER_BID,
                    bidEntity.getProduct().getProductId());
        }

        return bidEntity.toDomainModel();
    }

    @Override
    public Page<ProductBidDetailsResponse> getBidsForUser(final UUID userId, final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);

        return bidRepository.findBidEntitiesByUserEntity_UserIdOrderByBidTimeDesc(userId, pageable)
                .map(bidEntity -> new ProductBidDetailsResponse(bidEntity.getProduct(), bidEntity.getBidAmount()));
    }
}
