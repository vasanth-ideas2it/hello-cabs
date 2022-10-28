package com.hellocabs.dto;

import lombok.Data;

@Data
public class LoginDto {
    private long customerMobileNumber;
    private String password;
    private long cabMobileNumber;
}
