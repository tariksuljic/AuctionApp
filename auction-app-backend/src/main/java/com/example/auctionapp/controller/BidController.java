package com.example.auctionapp.controller;

import com.example.auctionapp.model.Bid;
import com.example.auctionapp.request.BidRequest;
import com.example.auctionapp.service.BidService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bids")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/place-bid")
    public Bid placeBid(@RequestBody final BidRequest bidRequest) {
        return bidService.placeBid(bidRequest);
    }
}
