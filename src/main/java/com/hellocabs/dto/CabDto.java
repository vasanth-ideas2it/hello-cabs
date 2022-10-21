package com.hellocabs.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CabDto {


    private int id;
    @NotNull(message = "Driver Name Not to be Empty :: Please Enter The Name")
    private String driverName;

    @NotNull(message = "Cab Number Not to be Empty :: Please Enter The Cab Number")
    private String cabNumber;

    @NotNull(message = "Gender Not to be Empty :: Please Enter The Name")
    private String gender;

    @NotNull(message = "mobile Number Not to be Empty :: Please Enter The Number")
    private long mobileNumber;

    private String email;

    @NotNull(message = "License Number Not to be Empty :: Please Enter The License Number")
    private String licenseNumber;

    private double driverRating;

    @NotNull(message = "Cab Status Not to be Empty :: Please Enter The Cab Status")
    private String cabStatus;

    private List<RideDto> rides;

    @NotNull(message = "CarModel Not to be Empty :: Please Enter The CarModel")
    private String carModel;

    private String currentLocation;


}
