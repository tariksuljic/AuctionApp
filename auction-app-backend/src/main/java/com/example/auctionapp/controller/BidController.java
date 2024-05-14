package com.example.auctionapp.controller;

import com.example.auctionapp.model.Bid;
import com.example.auctionapp.request.BidRequest;
import com.example.auctionapp.response.ProductBidDetailsResponse;
import com.example.auctionapp.service.BidService;
import com.example.auctionapp.util.SecurityRoles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bids")
@SecurityRequirement(name = "JWT Security")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PostMapping("/place-bid")
    public Bid placeBid(@RequestBody final BidRequest bidRequest) {
        return bidService.placeBid(bidRequest);
    }

    @PreAuthorize(SecurityRoles.ALL)
    @GetMapping("/{userId}")
    public Page<ProductBidDetailsResponse> getBidsForUser(
            @PathVariable final UUID userId,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "8") final int size
    ) {
        return bidService.getBidsForUser(userId, page, size);
    }
}
