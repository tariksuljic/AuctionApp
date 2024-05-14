package com.example.auctionapp.controller;

import com.example.auctionapp.model.PaymentInfo;
import com.example.auctionapp.request.PaymentAddRequest;
import com.example.auctionapp.service.UserService;
import com.example.auctionapp.util.SecurityRoles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "JWT Security")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(SecurityRoles.ALL)
    @GetMapping("/{userId}/payment-info")
    public PaymentInfo getPaymentInfoByUser(@PathVariable final UUID userId) {
        return userService.getPaymentInfoByUser(userId);
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PostMapping("/{userId}/payment-info")
    public PaymentInfo addPaymentInfoToUser(@PathVariable final UUID userId,
                                            @RequestBody final PaymentAddRequest paymentAddRequest) {
        return userService.addPaymentInfoToUser(userId, paymentAddRequest);
    }
}
