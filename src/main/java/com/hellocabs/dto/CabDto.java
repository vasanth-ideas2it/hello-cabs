/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellocabs.constants.HelloCabsConstants;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
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


    private Integer id;

    @NotBlank(message = HelloCabsConstants.DRIVER_NAME_NOT_BLANK)
    @Pattern(regexp = ("([a-zA-Z]){3,}"))
    private String driverName;

    @NotBlank(message = HelloCabsConstants.CAB_NUMBER_NOT_BLANK)
    @Size(min = 5, max = 15)
    private String cabNumber;

    @NotBlank(message = HelloCabsConstants.GENDER_NOT_BLANK)
    @Size(min = 1, max =10 )
    @Pattern(regexp = ("male|female|m|f|others"))
    private String gender;

    private Long mobileNumber;

    @Email(message = HelloCabsConstants.ENTER_VALID_EMAIL)
    @Size(min = 10, max = 30)
    private String email;

    @NotBlank(message = HelloCabsConstants.LICENSE_NOT_BLANK)
    @Size(min = 15, max = 30)
    private String licenseNumber;

    private Double driverRating;

    private String cabStatus;

    private Set<RideDto> rides;

    @NotBlank(message = HelloCabsConstants.CAR_MODEL_NOT_BLANK)
    @Size(min = 5, max = 20, message = HelloCabsConstants.CAR_MODEL_SIZE)
    private String carModel;

    @Size(min = 5, max = 15, message = HelloCabsConstants.CURRENT_LOCATION_SIZE)
    private String currentLocation;

    private Integer cabCategoryId;

    @NotBlank(message = "set password for your security")
    private String password;

    @JsonIgnore
    private boolean isActive;

}
