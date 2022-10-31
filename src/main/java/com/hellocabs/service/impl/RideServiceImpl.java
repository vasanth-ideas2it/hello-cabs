/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.logger.LoggerConfiguration;
import com.hellocabs.mapper.RideMapper;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import com.hellocabs.service.CabCategoryService;
import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;
import com.hellocabs.service.RideService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *   Class that provides implementation to all abstract
 *   methods that are available on ride service interface
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final CabService cabService;
    private final CabCategoryService cabCategoryService;
    private final CustomerService customerService;
    private final Logger logger = LoggerConfiguration
            .getInstance("RideServiceImpl.class");
    private static final int MAXIMUM_WAITING_TIME = 5;
    private static final int MINIMUM_BOOKING_HOUR = 4;

    /**
     * <p>
     *   Implement this method to get the Dto object
     *   and convert to entity object through mapper class
     *   then send the entity to repository layer to store
     *   in database
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be created
     * @return {@link int} created ride id
     *
     */
    private Ride createRide(RideDto rideDto) {
        Ride ride = rideRepository
                .save(RideMapper.convertRideDtoIntoRide(rideDto));
        logger.info(HelloCabsConstants.RIDE_CREATED + ride.getId());
        return ride;
    }

    /**
     * <p>
     *   Implement this method to fetch a particular ride using id
     *   if ride exists return ride else return a new ride object
     * </p>
     *
     * @param id {@link int} ride's id to be searched
     * @return {@link RideDto} searched ride object
     *
     */
    public RideDto searchRideById(int id) {
        Ride ride = rideRepository.findByIdAndIsCancelled(id, false);

        if (ride != null) {
            logger.info(HelloCabsConstants.RIDE_FOUND + ride);
            return RideMapper.convertRideIntoRideDto(ride);
        }
        logger.error(HelloCabsConstants.RIDE_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_FOUND);
    }

    /**
     * <p>
     *   Implement this method to retrieve all rides
     *   irrespective of id
     *   Returns empty set when no rides available
     * </p>
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
     * <p>
     *   Implement this method to update particular ride
     *   First update the required fields also enter the id
     *   then only existing object updated else if no id mentioned
     *   a new object will be created
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link String} updated ride
     *
     */
    public RideDto updateRide(RideDto rideDto) {
        Ride ride = rideRepository.save(RideMapper
                .convertRideDtoIntoRide(rideDto));
        logger.info(HelloCabsConstants.RIDE_UPDATED + ride);
        return RideMapper.convertRideIntoRideDto(ride);
    }

    /**
     * <p>
     *   Implement this method whenever a user wants to cancel
     *   the ride for some reason, also get the reason for cancel
     *   the ride from user
     * </p>
     *
     * @param id {@link int} ride details to be updated
     * @return {@link String} reason for ride cancellation
     *
     */
    public String deleteRideById(int id) {
        RideDto rideDto = searchRideById(id);

        if (rideDto != null) {
            rideDto.setIsCancelled(true);
            rideDto.setRideStatus("Cancelled");
            updateRide(rideDto);
            logger.info(HelloCabsConstants.RIDE_CANCELLED);
            return HelloCabsConstants.RIDE_CANCELLED;
        }
        logger.error(HelloCabsConstants.RIDE_NOT_FOUND);
        return HelloCabsConstants.RIDE_NOT_FOUND;
    }

    /**
     * <p>
     *   Implement this method whenever a customer books a ride
     *   and choose category by using category id and also
     *   ride details like locations, time, etc.,
     *   It show locations and category details from which
     *   customer have to select location and category and
     *   then the ride is created also ride status changed
     *   to booked
     * </p>
     *
     * @param rideDto {@link RideDto} ride details of a customer
     * @param customerId {@link int} customer who booked a cab
     * @return cabDtos {@link Set<CabDto>} list of cab that are
     *              available on particular location
     *
     */
    public String bookRide(RideDto rideDto, int customerId) {
        CustomerDto customerDto = customerService
                .viewCustomerById(customerId);
        Set<RideDto> rideDtos = new HashSet<>();
        rideDto.setRideStatus(HelloCabsConstants.RIDE_BOOKED);
        rideDto.setRideBookedTime(LocalDateTime.now());
        RideDto rideDto1 = RideMapper
                .convertRideIntoRideDto(createRide(rideDto));

        if (null != customerDto.getRides()) {
            rideDtos= customerDto.getRides();
            rideDtos.add(rideDto1);
        }
        rideDtos.add(rideDto1);
        customerDto.setRides(rideDtos);
        logger.info("Book ride " + customerDto);
        customerService.updateCustomer(customerDto);
        return HelloCabsConstants.RIDE_CREATED + rideDto1.getId()
                + HelloCabsConstants.WAITING_STATUS;
    }

    /**
     * <p>
     *   Implement this method is used when ride is booked, no
     *   cab categories were found on that location or
     *   no cab driver was accept this ride for
     *   some time, ride will be cancelled automatically
     *   and shows user a message to choose another cab category
     * </p>
     *
     * @param rideId {@link int}
     * @return rideDto {@link RideDto} updated rideDto
     *
     */
    public String confirmRide(int rideId) {
        RideDto rideDto = searchRideById(rideId);

        if ((MAXIMUM_WAITING_TIME < (LocalDateTime.now().getMinute())
                - rideDto.getRideBookedTime().getMinute())
                && ("Booked").equalsIgnoreCase(rideDto.getRideStatus())) {
            deleteRideById(rideId);
            return HelloCabsConstants.CANCELLED_DUE_TO_UNAVAILABILITY;
        }
        return HelloCabsConstants.SEARCHING_CABS;
    }

    /**
     * <p>
     *   Implement this method is often used to change the status of both
     *   ride and cab by passing the user input as an object
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @return {@link CabDto} assigned cab details
     *
     */
    public CabDto updateStatus(StatusDto statusDto) {        
        int rideId = statusDto.getRideId();        
        int cabId = statusDto.getCabId();
        RideDto rideDto = searchRideById(rideId);
        
        if (null != rideDto) {
            logger.info(HelloCabsConstants.RIDE_FOUND + rideDto);
            rideDto.setRideStatus(statusDto.getRideStatus());
            CabDto cabDto = cabService.displayCabDetailsById(cabId);
            return updateStatusInfo(statusDto, rideDto, cabDto);

        }
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_FOUND);
    }

    /**
     * <p>
     *   Implement this method is often used to change the status of both
     *   ride and cab by passing the rideStatus, and respective objects
     * </p>
     *
     * @param statusDto {@link StatusDto} status of ride
     * @param rideDto {@link RideDto} change ride's ride status
     * @param cabDto {@link CabDto} change cab's cab status
     * @return {@link CabDto} updated cabDto
     *
     */
    private CabDto updateStatusInfo(StatusDto statusDto,
            RideDto rideDto, CabDto cabDto) {
        String rideStatus = statusDto.getRideStatus();
        CabCategoryDto cabCategoryDto = cabCategoryService
                .getCabCategoryById(statusDto.getCategoryId());

        switch (rideStatus.toLowerCase()) {
            case "accepted" :
                cabDto.setCabStatus(HelloCabsConstants.CAB_UNAVAILABLE);
                break;

            case "picked" :
                rideDto.setRidePickedTime(LocalDateTime.now());
                rideDto.setRideStatus(rideStatus);
                cabDto.setCabStatus(HelloCabsConstants.CAB_ON_RIDE);
                break;

            case "dropped" :
                rideDto.setRideDroppedTime(LocalDateTime.now());
                rideDto.setRideStatus(rideStatus);
                cabDto.setCabStatus(HelloCabsConstants.CAB_AVAILABLE);
                Set<RideDto> rideDtos = cabDto.getRides();
                rideDtos.add(rideDto);
                cabDto.setRides(rideDtos);
                cabDto.setCurrentLocation(rideDto.getDropLocation()
                        .getLocationName());
                double price = calculateTravelFare(rideDto, cabCategoryDto);
                rideDto.setPrice(price);
                break;

            case "cancelled" :
                rideDto.setRideStatus(rideStatus);
                cabDto.setCabStatus(HelloCabsConstants.CAB_AVAILABLE);
                break;

            default:
                cabDto.setCabStatus("UnAvailable");
        }
        cabService.updateCabDetailsById(cabDto.getId(), cabDto);
        updateRide(rideDto);
        return cabDto;
    }

    /**
     * <p>
     * Method used to Calculate TravelFare by using PickUpTime And DropTime
     * </p>
     * @param rideDto {@link RideDto, CabDto, CabCategory}rideDto,
     *                                   cabDto, cabCategory Object
     * @param cabCategoryDto {@link CabCategoryDto}
     * @return {@link Double}returns RidePrice by Time Of Travel
     */
    public double calculateTravelFare(RideDto rideDto,
            CabCategoryDto cabCategoryDto) {
        int pickTime = rideDto.getRidePickedTime().getHour();
        int droppedTime =rideDto.getRideDroppedTime().getHour();
        int timeDifference = (droppedTime - pickTime);
        double initialFare = cabCategoryDto.getInitialFare();
        double extraHourFare = cabCategoryDto.getExtraFarePerHour();
        double additionalFare = cabCategoryDto.getPeakHourFare();
        boolean isPeakHour = (Integer.toString(pickTime)
                .matches(HelloCabsConstants.PEAK_HOUR_REGEX));
        double totalFare = 0;

        if (MINIMUM_BOOKING_HOUR > (timeDifference)) {
             totalFare = isPeakHour
                     ? (initialFare + additionalFare)
                     : initialFare;

        } else if (MINIMUM_BOOKING_HOUR < (timeDifference)) {
            double fare = initialFare
                    + ((timeDifference - 4) * extraHourFare);
            totalFare = isPeakHour ? (fare + additionalFare) : fare;
        }
        return totalFare;
    }

}
