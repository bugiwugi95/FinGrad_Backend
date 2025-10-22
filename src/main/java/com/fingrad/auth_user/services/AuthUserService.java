package com.fingrad.auth_user.services;

import com.fingrad.auth_user.dto.response.MessageResponse;
import com.fingrad.auth_user.dto.request.RegistrationRequest;

public interface AuthUserService {

 MessageResponse registrationUser(RegistrationRequest request);

}
