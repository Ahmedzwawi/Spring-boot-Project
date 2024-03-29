package com.codewithmeprojects.services.auth;

import com.codewithmeprojects.dto.SignUpRequest;
import com.codewithmeprojects.dto.userDto;

public interface AuthService {

    public userDto createCustomer(SignUpRequest SignUpRequest);
    public boolean hasCustomerWithEmail(String email);
}
