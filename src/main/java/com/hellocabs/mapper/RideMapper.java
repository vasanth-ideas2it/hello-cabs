/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.mapper;

import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Ride;

/**
 *
 * Used to convert DTO to simple POJO and vice-versa
 *
 * This file is created on 20/10/2022
 * @author : Pradeep
 *
 */
public class RideMapper {

    /**
     *
     * Used to convert rideDto into ride entity
     *
     * @param rideDto {@link RideDto}
     * @return ride {@link Ride}
     *
     */
    public static Ride convertRideDtoIntoRide(RideDto rideDto) {
        Ride ride = new Ride();
        ride.setId(rideDto.getId());
        ride.setRideTime(rideDto.getRideTime());
        ride.setPrice(rideDto.getPrice());
        ride.setPassengerMobileNumber(rideDto.getPassengerMobileNumber());
        ride.setRating(rideDto.getRating());
        ride.setRideStatus(rideDto.getRideStatus());
        //ride.setPickupLocation(rideDto.getPickupLocation());
       // ride.setDropLocation(rideDto.getDropLocation());
        return ride;
    }

    /**
     *
     * Used to convert ride into rideDto entity
     *
     * @param ride {@link Ride}
     * @return rideDto {@link RideDto}
     *
     */
    public static RideDto convertRideIntoRideDto(Ride ride) {
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());
        rideDto.setRideTime(ride.getRideTime());
        rideDto.setPrice(ride.getPrice());
        rideDto.setPassengerMobileNumber(ride.getPassengerMobileNumber());
        rideDto.setRating(ride.getRating());
        rideDto.setRideStatus(ride.getRideStatus());
       // rideDto.setPickupLocation(ride.getPickupLocation());
        //rideDto.setDropLocation(ride.getDropLocation());
        return rideDto;
    }
}
