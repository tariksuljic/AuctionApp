package com.example.auctionapp.service.implementation;

import com.example.auctionapp.exceptions.payment.PaymentFailedException;
import com.example.auctionapp.request.ChargeRequest;
import com.example.auctionapp.response.PaymentResponse;
import com.example.auctionapp.util.CustomerUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    private static final Logger logger = LoggerFactory.getLogger(StripeService.class);

    @Value("${stripeProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;

        logger.info("Stripe API key initialized");
    }

    public PaymentResponse createPaymentIntent(final ChargeRequest request) {
        try {
            final Customer customer = CustomerUtil
                    .findOrCreateCustomer(request.getCustomerEmail(), request.getCustomerName());

            final PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(Long.parseLong(String.valueOf(request.getProduct().getHighestBid())) * 100)
                    .setCurrency("usd")
                    .setCustomer(customer.getId())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
                    .build();

            final PaymentIntent paymentIntent = PaymentIntent.create(params);

            return new PaymentResponse(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            logger.error("Error creating payment intent: {}", e.getMessage(), e);

            throw new PaymentFailedException(e.getMessage());
        }
    }
}
