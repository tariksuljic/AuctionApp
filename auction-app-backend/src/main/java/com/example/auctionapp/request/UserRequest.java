package com.example.auctionapp.request;

import com.example.auctionapp.entity.enums.UserRoles;

public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRoles role;

    public UserRequest(final String firstName,
                       final String lastName,
                       final String email,
                       final String password,
                       final UserRoles role
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRoles getRole() {
        return this.role;
    }

    public void setRole(final UserRoles role) {
        this.role = role;
    }
}
