/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.dto.RideDto;
import com.hellocabs.mapper.RideMapper;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import com.hellocabs.service.RideService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Implementation to save new ride or search,
 * update, and delete existing ride details
 *
 * This file is created on 20/10/2022
 * @author : Pradeep
 *
 */
@Service
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;

    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    /**
     *
     * Add a ride into repository by
     * converting the dto to model
     *
     * @param rideDto {@link RideDto} ride details to be created
     * @return id {@link int} created ride id
     *
     */
    public int createRide(RideDto rideDto) {
        Ride ride = rideRepository
                .save(RideMapper.convertRideDtoIntoRide(rideDto));
        return ride.getId();
    }

    /**
     *
     * Search a ride by using id
     *
     * @param id {@link int} id to be searched
     * @return rideDto {@link RideDto} searched ride details
     *
     */
    public RideDto searchRideById(int id) {
        Ride ride = rideRepository.findById(id).orElse(null);
        return (ride != null)
                ? RideMapper.convertRideIntoRideDto(ride)
                :null;
    }

    /**
     *
     * Retrieve all rides
     *
     * @return {@link Set<RideDto>} set of all rides
     *
     */
    public Set<RideDto> retrieveRides() {
        return rideRepository.findAll().stream()
                .map(RideMapper::convertRideIntoRideDto)
                .collect(Collectors.toSet());
    }

    /**
     *
     * Update a ride by using additional information
     *
     * @param rideDto {@link RideDto} ride object to be updated
     * @return rideDto {@link RideDto} updated object
     *
     */
    public RideDto updateRide(RideDto rideDto) {
        Ride ride = rideRepository.save(RideMapper.convertRideDtoIntoRide(rideDto));
        return RideMapper.convertRideIntoRideDto(ride);
    }

    /**
     *
     * Delete a ride by using id
     *
     * @param id {@link int} ride to be deleted
     * @return true/false {@link boolean} deleted ride's id
     *
     */
    public boolean deleteRideById(int id) {
        Ride ride = rideRepository.findById(id).orElse(null);

        if (ride != null) {
            rideRepository.delete(ride);
            return true;
        }
        return false;
    }
}
