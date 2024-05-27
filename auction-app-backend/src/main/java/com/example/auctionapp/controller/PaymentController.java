package com.example.auctionapp.controller;

import com.example.auctionapp.model.PaymentInfo;
import com.example.auctionapp.request.PaymentAddRequest;
import com.example.auctionapp.request.StripePaymentAddRequest;
import com.example.auctionapp.service.PaymentService;
import com.example.auctionapp.util.SecurityRoles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@SecurityRequirement(name = "JWT Security")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PostMapping("/stripe-payment-info")
    public PaymentInfo addStripePaymentInfo(@RequestBody final StripePaymentAddRequest stripePaymentAddRequest) {
        return this.paymentService.addStripePaymentInfo(stripePaymentAddRequest);
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PostMapping("/add-payment-info")
    public PaymentInfo addNewPaymentInfo(@RequestBody final PaymentAddRequest paymentAddRequest) {
        return this.paymentService.addNewPaymentInfo(paymentAddRequest);
    }
}
