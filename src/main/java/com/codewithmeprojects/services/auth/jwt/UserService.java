package com.codewithmeprojects.services.auth.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailService() ;
}
