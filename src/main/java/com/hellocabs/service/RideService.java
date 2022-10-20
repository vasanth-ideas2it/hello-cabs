/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service;

import com.hellocabs.dto.RideDto;

import java.util.Set;

/**
 * Interface that contains abstract and static methods
 * create, search, update and delete a ride
 *
 * This file is created on 20/10/2022
 * @author : Pradeep
 *
 */
public interface RideService {

    /**
     *
     * Add a ride into repository by
     * converting the dto to model
     *
     * @param rideDto {@link RideDto} ride details to be created
     * @return id {@link int} created ride id
     *
     */
    int createRide(RideDto rideDto);

    /**
     *
     * Search a ride by using id
     *
     * @param id {@link int} id to be searched
     * @return rideDto {@link RideDto} searched ride details
     *
     */
    RideDto searchRideById(int id);

    /**
     *
     * Retrieve all rides
     *
     * @return {@link Set<RideDto>} set of all rides
     *
     */
    Set<RideDto> retrieveRides();

    /**
     *
     * Update a ride by using additional information
     *
     * @param rideDto {@link RideDto} ride object to be updated
     * @return rideDto {@link RideDto} updated object
     *
     */
    RideDto updateRide(RideDto rideDto);

    /**
     *
     * Delete a ride by using id
     *
     * @param id {@link int} ride to be deleted
     * @return true/false {@link boolean} deleted ride's id
     *
     */
    boolean deleteRideById(int id);
}
