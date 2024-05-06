package com.example.auctionapp.entity.enums;

public enum NotificationType {
    HIGHEST_BID("Congratulations! You are the highest bidder."),
    LOWER_BID("There are higher bids than yours. You could give a second try.");

    public final String label;

    private NotificationType(String label) {
        this.label = label;
    }
}
