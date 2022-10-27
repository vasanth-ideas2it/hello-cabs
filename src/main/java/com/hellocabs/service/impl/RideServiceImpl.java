/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.logger.LoggerConfiguration;
import com.hellocabs.mapper.RideMapper;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import com.hellocabs.service.CabCategoryService;
import com.hellocabs.service.CabService;
import com.hellocabs.service.LocationService;
import com.hellocabs.service.RideService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    private CabService cabService;
    private CabCategoryService cabCategoryService;
    private LocationService locationService;
    private final Logger logger = LoggerConfiguration
            .getInstance("RideController.class");

    public RideServiceImpl(RideRepository rideRepository,
            CabService cabService, CabCategoryService cabCategoryService,
            LocationService locationService) {
        this.rideRepository = rideRepository;
        this.cabService = cabService;
        this.cabCategoryService = cabCategoryService;
        this.locationService = locationService;
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

    /**
     *
     * After display the ride fare and if ride is booked
     *
     * @param rideId {@link int}
     * @return rideDto {@link RideDto} updated rideDto
     */
    public String confirmRide(int rideId) {
        RideDto rideDto = searchRideById(rideId);

        if ((5 < (LocalDateTime.now().getMinute())
                - rideDto.getRideTime().getMinute())
                && ("Booked").equalsIgnoreCase(rideDto.getRideStatus())) {
            deleteRideById(rideId);
            return "Ride cancelled due to unavailability \n Please try again";
        }
        return "Looking for cabs nearby";
    }

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
    public CabDto updateStatus(int rideId, int cabId, String rideStatus, String cabStatus) {
        RideDto rideDto = searchRideById(rideId);

        if (null != rideDto) {
            rideDto.setRideStatus(rideStatus);
            CabDto cabDto = cabService.displayCabDetailsById(cabId);
            cabDto.setCabStatus(cabStatus);

            if ("Dropped".equalsIgnoreCase(rideStatus)) {
                Set<RideDto> rideDtos = cabDto.getRides();
                rideDtos.add(rideDto);
                cabDto.setRides(rideDtos);
                cabDto.setCurrentLocation(rideDto.getDropLocation()
                        .getLocationName());
            }
            cabService.updateCabDetailsById(cabId, cabDto);
            logger.info(" updated cabDto");
            return cabDto;
        }
        return new CabDto();
    }

    /**
     *
     * Book ride for a customer
     *
     * @param rideDto {@link RideDto} ride details of a customer
     * @return cabDtos {@link Set<CabDto>} list of cab that are
     * available on particular location
     *
     */
    public String bookRide(RideDto rideDto, int categoryId) {
        LocationDto pickupPoint = locationService
                .getLocationById(rideDto.getPickupLocation().getId());
        CabCategoryDto cabCategoryDto = cabCategoryService
                .getCabCategoryById(categoryId);
        cabCategoryDto.getCabDtos()
                .stream().filter(cabDto -> pickupPoint.getLocationName()
                        .equals(cabDto.getCurrentLocation()) && (("Available")
                        .equalsIgnoreCase(cabDto.getCabStatus())));
        rideDto.setRideStatus("Booked");
        rideDto.setRideTime(LocalDateTime.now());
        int id = createRide(rideDto);
        return "Ride booked with id " + id + " waiting for driver to accept";
    }

    /**
     *
     * Shows all available locations that are provided by the cab service
     *
     * @return {@link Set<LocationDto>}all available locations
     */
    public Set<LocationDto> displayAllLocations() {
        return new HashSet<>(locationService.retrieveAllLocations());
    }

    /**
     *
     * Shows all cab categories that are provided by the cab service
     *
     * @return {@link Set<CabCategoryDto>}all available categories
     */
    public Set<CabCategoryDto> displayAllCabCategories() {
        return new HashSet<>(cabCategoryService.retrieveAllCabCategories());
    }
}
