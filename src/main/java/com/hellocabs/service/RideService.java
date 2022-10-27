/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.LocationDto;
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

    /**
     *
     * After display the ride fare and if ride is booked
     *
     * @param rideId {@link int}
     * @return rideDto {@link RideDto} updated rideDto
     */
    String confirmRide(int rideId);

    /**
     *
     * Often change the status of the ride and cab
     *
     * @param rideId {@link int} ride id
     * @param cabId {@link int} cab id to be assigned
     * @param rideStatus {@link String} ride status
     * @param cabStatus {@link String} cab status
     * @return {@link CabDto}assigned cab details
     */
    CabDto updateStatus(int rideId, int cabId, String rideStatus, String cabStatus);

    /**
     *
     * Book ride for a customer
     *
     * @param rideDto {@link RideDto} ride details of a customer
     * @return cabDtos {@link Set<CabDto>} list of cab that are
     * available on particular location
     *
     */
    String bookRide(RideDto rideDto, int categoryId);

    /**
     *
     * Shows all available locations that are provided by the cab service
     *
     * @return {@link Set<LocationDto>}all available locations
     */
    Set<LocationDto> displayAllLocations();

    /**
     *
     * Shows all cab categories that are provided by the cab service
     *
     * @return {@link Set<CabCategoryDto>}all available categories
     */
    Set<CabCategoryDto> displayAllCabCategories();
}
