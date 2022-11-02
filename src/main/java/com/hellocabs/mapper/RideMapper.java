/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.mapper;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.RideDto;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Customer;
import com.hellocabs.model.Ride;
import org.apache.log4j.Logger;

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

    private static final  Logger logger = LoggerConfiguration
            .getInstance(HelloCabsConstants.RIDE_MAPPER);

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
        logger.info("In conversion " + rideDto.getCabId());
        Ride ride = new Ride();
        ride.setId(rideDto.getId());
        ride.setRideBookedTime(rideDto.getRideBookedTime());
        ride.setRidePickedTime(rideDto.getRidePickedTime());
        ride.setRideDroppedTime(rideDto.getRideDroppedTime());
        ride.setPrice(rideDto.getPrice());
        ride.setPassengerMobileNumber(rideDto.getPassengerMobileNumber());
        ride.setRating(rideDto.getRating());
        ride.setRideStatus(rideDto.getRideStatus());
        ride.setPickupLocation(LocationMapper
                .locationDtoToLocation(rideDto.getPickupLocation()));
        ride.setIsCancelled(rideDto.getIsCancelled());
        ride.setFeedback(rideDto.getFeedback());
        ride.setDropLocation(LocationMapper
                .locationDtoToLocation(rideDto.getDropLocation()));
        Cab cab = new Cab();
        Customer customer = new Customer();

        if (0 != rideDto.getCabId()) {
            cab.setId(rideDto.getCabId());
            ride.setCab(cab);
        }

        if (0 != rideDto.getCustomerId()) {
            customer.setCustomerId(rideDto.getCustomerId());
            ride.setCustomer(customer);
        }
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
        rideDto.setFeedback(ride.getFeedback());
        rideDto.setPickupLocation(LocationMapper
                .locationToLocationDto(ride.getPickupLocation()));
        rideDto.setDropLocation(LocationMapper
                .locationToLocationDto(ride.getDropLocation()));

        if (null != ride.getCab()) {
            rideDto.setCabId((ride.getCab().getId()));
        }

        if (null != ride.getCustomer()) {
            rideDto.setCustomerId(ride.getCustomer().getCustomerId());
        }
        return rideDto;
    }
}
