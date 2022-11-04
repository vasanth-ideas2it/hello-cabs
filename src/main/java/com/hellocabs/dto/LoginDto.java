/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.hellocabs.constants.HelloCabsConstants;
import javax.validation.constraints.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class LoginDto {

    @NotNull(message = HelloCabsConstants.MOBILE_NUMBER_NOT_BLANK)
    @Range(min = 10, message = HelloCabsConstants.VALID_PHONE_NUMBER)
    private Long mobileNumber;

    @NotBlank(message = HelloCabsConstants.PASSWORD_REQUIRED)
    @Pattern(regexp = HelloCabsConstants.PASSWORD)
    private String password;
}
