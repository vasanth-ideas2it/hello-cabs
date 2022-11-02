/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import java.util.Set;
import javax.validation.constraints.*;

import com.hellocabs.constants.HelloCabsConstants;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
/**
 * <h> CabDto </h>
 * <p>
 * Class is an Dto class which is define the possible field with respect to cab
 * and validate the user data by using validated annotation respectively
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@Data
@Validated
public class CabDto {


    private int id;
    @NotBlank(message = HelloCabsConstants.DRIVER_NAME_NOT_BLANK)
    @Size(min = 2, max = 30)
    private String driverName;

    @NotBlank(message = HelloCabsConstants.CAB_NUMBER_NOT_BLANK)
    @Size(min = 5, max = 15)
    private String cabNumber;

    @NotBlank(message = HelloCabsConstants.GENDER_NOT_BLANK)
    @Size(min = 4, max = 10)
    private String gender;

    @Digits(integer = 10, fraction = 0)
    private long mobileNumber;

    @Email(message = HelloCabsConstants.ENTER_VALID_EMAIL)
    @Size(min = 10, max = 30)
    private String email;

    @NotBlank(message = HelloCabsConstants.LICENSE_NOT_BLANK)
    @Size(min = 15, max = 30)
    private String licenseNumber;

    private double driverRating;

    @NotBlank(message = HelloCabsConstants.CAB_STATUS_NOT_BLANK)
    @Size(min = 5, max = 20)
    private String cabStatus;

    private Set<RideDto> rides;

    @NotBlank(message = HelloCabsConstants.CAR_MODEL_NOT_BLANK)
    @Size(min = 5, max = 20, message = HelloCabsConstants.CAR_MODEL_SIZE)
    private String carModel;

    @Size(min = 5, max = 15, message = HelloCabsConstants.CURRENT_LOCATION_SIZE)
    private String test;

    private int cabCategoryId;

    private String password;

    private boolean isActive;

}
