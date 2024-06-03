package com.example.auctionapp.controller;

import com.example.auctionapp.request.ChargeRequest;
import com.example.auctionapp.response.PaymentResponse;
import com.example.auctionapp.service.implementation.StripeService;
import com.example.auctionapp.util.SecurityRoles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stripe")
@SecurityRequirement(name = "JWT Security")
public class StripeController {
    private static final Logger logger = LoggerFactory.getLogger(StripeController.class);
    private final StripeService stripeService;

    public StripeController(final StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PostMapping("/checkout")
    public PaymentResponse integratedCheckout(@RequestBody final ChargeRequest request) {
        logger.info("Processing integrated checkout for customer: {}", request.getCustomerEmail());

        return stripeService.createPaymentIntent(request);
    }
}
