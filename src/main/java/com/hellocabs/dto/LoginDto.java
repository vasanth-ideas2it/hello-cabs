/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginDto {

    private Long MobileNumber;

    @NotBlank(message = "Enter your security password")
    @Pattern(regexp = ("([a-zA-Z0-9!@#$%^&*]{8,16})"))
    private String password;
}
