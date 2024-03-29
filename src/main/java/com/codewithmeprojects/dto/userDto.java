package com.codewithmeprojects.dto;


import com.codewithmeprojects.enums.userRole;
import lombok.Data;

@Data
public class userDto {
    private  Long id;

    private String name;

    private String email;

    private userRole userRole;


}
