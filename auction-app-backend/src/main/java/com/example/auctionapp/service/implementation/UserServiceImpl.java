package com.example.auctionapp.service.implementation;

import com.example.auctionapp.entity.PaymentInfoEntity;
import com.example.auctionapp.entity.UserEntity;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.model.PaymentInfo;
import com.example.auctionapp.repository.PaymentInfoRepository;
import com.example.auctionapp.repository.UserRepository;
import com.example.auctionapp.request.PaymentAddRequest;
import com.example.auctionapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PaymentInfoRepository paymentInfoRepository;

    public UserServiceImpl(UserRepository userRepository, PaymentInfoRepository paymentInfoRepository) {
        this.userRepository = userRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    @Override
    public PaymentInfo getPaymentInfoByUser(final UUID userId) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with the given ID does not exist"));

        return userEntity.getPaymentInfo().toDomainModel();
    }

    @Override
    public PaymentInfo addPaymentInfoToUser(final UUID userId, final PaymentAddRequest paymentRequest) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with the given ID does not exist"));

        PaymentInfoEntity paymentInfoEntity = new PaymentInfoEntity();

        paymentInfoEntity.setCardNumber(paymentRequest.getCardNumber());
        paymentInfoEntity.setAddress(paymentRequest.getAddress());
        paymentInfoEntity.setCity(paymentRequest.getCity());
        paymentInfoEntity.setZipCode(paymentRequest.getZipCode());
        paymentInfoEntity.setNameOnCard(paymentRequest.getNameOnCard());
        paymentInfoEntity.setCountry(paymentRequest.getCountry());
        paymentInfoEntity.setExpirationDate(paymentRequest.getExpirationDate());

        userEntity.setPaymentInfo(paymentInfoEntity);

        return paymentInfoRepository.save(paymentInfoEntity).toDomainModel();
    }
}
