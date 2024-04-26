package com.example.auctionapp.util;

public final class SecurityRoles {
    public static final String ADMIN = "hasAuthority('ADMIN')";
    public static final String USER = "hasAuthority('USER')";
    public static final String ALL = "hasAnyAuthority('ADMIN', 'USER')";
}
