package com.example.auctionapp.service;

import com.example.auctionapp.model.Bid;
import com.example.auctionapp.request.BidRequest;

public interface BidService {
    Bid placeBid(final BidRequest bidRequest);
}
