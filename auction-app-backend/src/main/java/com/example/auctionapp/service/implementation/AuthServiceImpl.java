package com.example.auctionapp.service.implementation;

import com.example.auctionapp.entity.RefreshTokenEntity;
import com.example.auctionapp.entity.UserEntity;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.model.RefreshToken;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.UserRepository;
import com.example.auctionapp.request.LoginRequest;
import com.example.auctionapp.request.UserRequest;
import com.example.auctionapp.response.JwtResponse;
import com.example.auctionapp.service.AuthService;
import com.example.auctionapp.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(final UserRepository userRepository,
                           final RefreshTokenService refreshTokenService,
                           final UserDetailsServiceImpl userDetailsService
    ) {
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public User signUp(final UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setRole(userRequest.getRole());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(userEntity).toDomainModel();
    }

    @Override
    public JwtResponse signIn(final LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final UserEntity user = userRepository.findUserEntityByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // generate access and refresh tokens
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = refreshTokenService.createRefreshToken(user.getEmail()).getToken();

        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public String refreshAccessToken(final String token) {
        final RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByToken(token)
                .map(RefreshToken::toEntity)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        final UserEntity user = userRepository.findUserEntityByEmail(refreshTokenEntity.getUserEntity().getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        refreshTokenService.verifyExpiration(refreshTokenEntity);

        return jwtService.generateToken(user);
    }

    @Override
    public void deleteRefreshToken(final String token) {
        refreshTokenService.deleteRefreshToken(token);
    }
}
