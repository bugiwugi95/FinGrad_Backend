package com.fingrad.auth_user.dto.request;

public record RegistrationRequest(

    String email,

    String phone,

    String password) {

}
