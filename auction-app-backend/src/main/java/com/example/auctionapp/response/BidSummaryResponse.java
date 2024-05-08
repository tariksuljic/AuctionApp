package com.example.auctionapp.response;

import java.math.BigDecimal;

public class BidSummaryResponse {
    private BigDecimal highestBid;
    private int bidsCount;

    public BidSummaryResponse(final BigDecimal highestBid, final int bidsCount) {
        this.highestBid = highestBid;
        this.bidsCount = bidsCount;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(final BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public int getBidsCount() {
        return this.bidsCount;
    }

    public void setBidsCount(final int bidsCount) {
        this.bidsCount = bidsCount;
    }
}
