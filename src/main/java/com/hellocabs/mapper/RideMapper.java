/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.mapper;

import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Location;
import com.hellocabs.model.Ride;

/**
 * <p>
 *   Class that used to convert the user input(dto) object to entity object
 *   and vice versa
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
public class RideMapper {

    /**
     * <p>
     *   Used this method to convert rideDto into ride entity
     *   when get input from user and store in database
     * </p>
     *
     * @param rideDto {@link RideDto} dto object to be converted
     * @return ride {@link Ride} converted object
     *
     */
    public static Ride convertRideDtoIntoRide(RideDto rideDto) {
        Ride ride = new Ride();
        ride.setId(rideDto.getId());
        ride.setRideBookedTime(rideDto.getRideBookedTime());
        ride.setRidePickedTime(rideDto.getRidePickedTime());
        ride.setRideDroppedTime(rideDto.getRideDroppedTime());
        ride.setPrice(rideDto.getPrice());
        ride.setPassengerMobileNumber(rideDto.getPassengerMobileNumber());
        ride.setRating(rideDto.getRating());
        ride.setRideStatus(rideDto.getRideStatus());
        Location location = new Location();
        location.setId(rideDto.getPickupLocation().getId());
        ride.setPickupLocation(location);
        ride.setIsCancelled(rideDto.getIsCancelled());
        ride.setDropLocation(LocationMapper
                .locationDtoToLocation(rideDto.getDropLocation()));
        return ride;
    }

    /**
     * <p>
     *   Used this method to convert ride entity into rideDto
     *   when to fetch data from database to show output to user
     * </p>
     *
     * @param ride {@link Ride} entity to be converted
     * @return rideDto {@link RideDto} converted object
     *
     */
    public static RideDto convertRideIntoRideDto(Ride ride) {
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());
        rideDto.setRideBookedTime(ride.getRideBookedTime());
        rideDto.setRidePickedTime(ride.getRidePickedTime());
        rideDto.setRideDroppedTime(ride.getRideDroppedTime());
        rideDto.setPrice(ride.getPrice());
        rideDto.setPassengerMobileNumber(ride.getPassengerMobileNumber());
        rideDto.setRating(ride.getRating());
        rideDto.setRideStatus(ride.getRideStatus());
        rideDto.setIsCancelled(ride.getIsCancelled());
        rideDto.setPickupLocation(LocationMapper
                .locationToLocationDto(ride.getPickupLocation()));
        rideDto.setDropLocation(LocationMapper
                .locationToLocationDto(ride.getDropLocation()));
        return rideDto;
    }
}
