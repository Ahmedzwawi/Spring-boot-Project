package com.codewithmeprojects.dto;

import com.codewithmeprojects.enums.userRole;
import lombok.Data;

@Data
public class AuthenticationResponse {


        private String jwt ;

        private userRole userRole ;

        private Long userId ;

}
