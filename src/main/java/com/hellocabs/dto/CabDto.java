package com.hellocabs.dto;

import java.util.Set;
import javax.validation.constraints.*;

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
    @NotBlank(message = "Driver Name Not to be Empty :: Please Enter The Name")
    @Size(min = 2, max = 30)
    private String driverName;

    @NotBlank(message = "Cab Number Not to be Empty :: Please Enter The Cab Number")
    @Size(min = 5, max = 15)
    private String cabNumber;

    @NotBlank(message = "Gender Not to be Empty :: Please Enter The Gender")
    @Size(min = 4, max = 10)
    private String gender;

    @Digits(integer = 10, fraction = 0)
    private long mobileNumber;

    @Email(message = " Enter the valid Mail")
    @Size(min = 10, max = 30)
    private String email;

    @NotBlank(message = "License Number Not to be Empty :: Please Enter The License Number")
    @Size(min = 15, max = 30)
    private String licenseNumber;

    private double driverRating;

    @NotBlank(message = "Cab Status Not to be Empty :: Please Enter The Cab Status")
    @Size(min = 5, max = 20)
    private String cabStatus;

    private Set<RideDto> rides;

    @NotBlank(message = "CarModel Not to be Empty :: Please Enter The CarModel")
    @Size(min = 5, max = 20, message = "CarModel Should be in 5 to 20 characters")
    private String carModel;

    @Size(min = 5, max = 15, message = "Current Location Should be in between 10 to 15 Characters")
    private String currentLocation;

    private CabCategoryDto cabCategoryDto ;

    private String password;

    private boolean isActive;

}
