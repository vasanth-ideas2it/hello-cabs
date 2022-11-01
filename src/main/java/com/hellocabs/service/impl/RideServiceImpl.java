/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.FeedBackDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.mapper.RideMapper;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import com.hellocabs.service.CabCategoryService;
import com.hellocabs.service.CabService;
import com.hellocabs.service.RideService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final Logger logger = LoggerConfiguration
            .getInstance(HelloCabsConstants.RIDE_SERVICE_CLASS);
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
     * @param feedBackDto {@link FeedBackDto} feedback and ride status details
     * @return {@link String} reason for ride cancellation
     *
     */
    public String deleteRide(FeedBackDto feedBackDto) {
        RideDto rideDto = searchRideById(feedBackDto.getRideId());

        if (rideDto != null) {

            if (!HelloCabsConstants.RIDE_COMPLETED
                    .equalsIgnoreCase(rideDto.getRideStatus())) {
                rideDto.setIsCancelled(true);
                rideDto.setRideStatus(HelloCabsConstants.RIDE_IGNORED);
                rideDto.setFeedback(feedBackDto.getFeedback());
                updateRide(rideDto);
                logger.info(HelloCabsConstants.RIDE_CANCELLED);
                return HelloCabsConstants.RIDE_CANCELLED;
            }
            throw new HelloCabsException(HelloCabsConstants
                    .RIDE_NOT_CANCELLED);
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
     * @param bookDto {@link BookDto} ride details of a customer
     * @return {@link String} booking id
     *
     */
    public String bookRide(BookDto bookDto) {
        RideDto rideDto = new RideDto();
        rideDto.setPassengerMobileNumber(bookDto.getPassengerMobileNumber());
        rideDto.setPickupLocation(bookDto.getPickupLocation());
        rideDto.setDropLocation(bookDto.getDropLocation());
        rideDto.setCustomerDto(bookDto.getCustomerDto());
        rideDto.setRideStatus(HelloCabsConstants.RIDE_BOOKED);
        rideDto.setRideBookedTime(LocalDateTime.now());
        RideDto rideDto1 = RideMapper
                .convertRideIntoRideDto(createRide(rideDto));
        return HelloCabsConstants.RIDE_CREATED + rideDto1.getId()
                + HelloCabsConstants.WAITING_STATUS;
    }

    /**
     * <p>
     *   Used this method whenever to give rating and
     *   feedback for the ride
     * </p>
     *
     * @param ratingDto {@link RatingDto} get the feedback and
     *      rating for the ride when finished
     * @return {@link String} ride's rating
     *
     */
    public RideDto submitFeedBack(RatingDto ratingDto) {

        if (HelloCabsConstants.RIDE_COMPLETED
                .equalsIgnoreCase(ratingDto.getRideStatus())) {
            RideDto rideDto = searchRideById(ratingDto.getRideId());

            if (HelloCabsConstants.RIDE_PICKED
                    .equalsIgnoreCase(rideDto.getRideStatus())) {
                rideDto.setRideDroppedTime(LocalDateTime.now());
                rideDto.setRideStatus(ratingDto.getRideStatus());
                CabDto cabDto = cabService.displayCabDetailsById(
                        rideDto.getCabDto().getId());
                logger.info(cabDto.getId());
                logger.info(cabDto.getCabCategoryId());
                cabDto.setCabStatus(HelloCabsConstants.CAB_AVAILABLE);
                cabDto.setCurrentLocation(rideDto.getDropLocation()
                        .getLocationName());
                double price = calculateTravelFare(rideDto,
                        cabDto.getCabCategoryId());
                rideDto.setPrice(price);
                rideDto.setRating(ratingDto.getRating());
                rideDto.setFeedback(ratingDto.getFeedback());
                cabService.updateCabDetailsById(cabDto.getId(), cabDto);
                return updateRide(rideDto);
            }
            throw new HelloCabsException(HelloCabsConstants
                    .CUSTOMER_NOT_PICKED);
        }
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
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
                && (HelloCabsConstants.RIDE_BOOKED)
                .equalsIgnoreCase(rideDto.getRideStatus())) {
            rideDto.setIsCancelled(true);
            rideDto.setFeedback(HelloCabsConstants
                    .CANCELLED_DUE_TO_UNAVAILABILITY);
            rideDto.setRideStatus(HelloCabsConstants.RIDE_IGNORED);
            updateRide(rideDto);
            return HelloCabsConstants.CANCELLED_DUE_TO_UNAVAILABILITY;
        }
        logger.info(HelloCabsConstants.SEARCHING_CABS);
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
            logger.info(HelloCabsConstants.RIDE_FOUND);
            rideDto.setRideStatus(statusDto.getRideStatus());
            CabDto cabDto = cabService.displayCabDetailsById(cabId);
            rideDto.setCabDto(cabDto);
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

        switch (rideStatus.toLowerCase()) {
            case "accepted" :
                cabDto.setCabStatus(HelloCabsConstants.CAB_UNAVAILABLE);
                break;

            case "picked" :
                rideDto.setRidePickedTime(LocalDateTime.now());
                rideDto.setRideStatus(rideStatus);
                rideDto.setCabDto(cabDto);
                cabDto.setCabStatus(HelloCabsConstants.CAB_ON_RIDE);
                break;

            case "cancelled" :
                rideDto.setRideStatus(rideStatus);
                cabDto.setCabStatus(HelloCabsConstants.CAB_AVAILABLE);
                break;

            default:
                throw new HelloCabsException(HelloCabsConstants
                        .STATUS_NOT_FOUND, new IllegalArgumentException());
        }
        logger.info(HelloCabsConstants.STATUS_UPDATED + rideStatus);
        cabService.updateCabDetailsById(cabDto.getId(), cabDto);
        updateRide(rideDto);
        return cabDto;
    }

    /**
     * <p>
     *   Calculate TravelFare by using time difference between
     *   PickUpTime And DropTime, if time difference exceeds basic
     *   fare then extra charge will be added for every additional hour
     *   Also if ride is booked in peak hour respective price has been
     *   calculated for base time and also for additional time
     * </p>
     *
     * @param rideDto {@link RideDto} rideDto
     * @param cabCategoryId {@link int} id to be searched
     * @return {@link Double}returns RidePrice by Time Of Travel
     *
     */
    private double calculateTravelFare(RideDto rideDto,
            int cabCategoryId) {
        logger.info(cabCategoryId);
        CabCategoryDto cabCategoryDto = cabCategoryService
                .getCabCategoryById(cabCategoryId);
        logger.info(cabCategoryDto.getInitialFare());
        logger.info(cabCategoryDto.getExtraFarePerHour());
        logger.info(cabCategoryDto.getPeakHourFare());

        if ((HelloCabsConstants.RIDE_COMPLETED)
                .equalsIgnoreCase(rideDto.getRideStatus())) {
            int pickTime = rideDto.getRidePickedTime().getHour();
            int droppedTime = rideDto.getRideDroppedTime().getHour();
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
            logger.info(HelloCabsConstants.TRAVEL_FARE + totalFare);
            return totalFare;
        }
        logger.info(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
    }
}