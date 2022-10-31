/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hellocabs.constants.HelloCabsConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * <p>
 *   A Dto object which is responsible for collect and display
 *   all possible information regarding to a ride from user through
 *   controller and further convert to Entity object through
 *   service using mapper classes
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Getter
@Setter
@Validated
public class RideDto {

    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideBookedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ridePickedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideDroppedTime;

    private double price;

    //@NotBlank(message = HelloCabsConstants.MOBILE_NUMBER_NOT_BLANK)
    //@Range(min = 0, max = 10, message = HelloCabsConstants.MOBILE_NUMBER)
    private long passengerMobileNumber;

    private double rating;

    @NotBlank(message = HelloCabsConstants.RIDE_STATUS_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.RIDE_STATUS_REGEX,
            message = HelloCabsConstants.RIDE_STATUS)
    private String rideStatus;

    private boolean isCancelled;

    //@NotBlank(message = HelloCabsConstants.PICKUP_LOCATION_NOT_EMPTY)
    private LocationDto pickupLocation;

    //@NotBlank(message = HelloCabsConstants.DROP_LOCATION_NOT_EMPTY)
    private LocationDto  dropLocation;

    private CabDto cabDto;

    //@NotBlank(message = HelloCabsConstants.CUSTOMER_ID_NOT_BLANK)
    private CustomerDto customerDto;

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    @Override
    public String toString() {
        return id + "" + passengerMobileNumber + pickupLocation
                + dropLocation.getId() + rideStatus + rating
                + cabDto.getId() + customerDto.getCustomerId();
    }
}

