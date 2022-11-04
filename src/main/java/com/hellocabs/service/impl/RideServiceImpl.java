/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.ReasonDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.enumeration.Status;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.mapper.CabMapper;
import com.hellocabs.mapper.RideMapper;
import com.hellocabs.model.Cab;
import com.hellocabs.model.CabCategory;
import com.hellocabs.model.Ride;
import com.hellocabs.repository.RideRepository;
import com.hellocabs.service.CabService;
import com.hellocabs.service.RideService;

import lombok.RequiredArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    private final Logger logger = LoggerConfiguration
            .getInstance(HelloCabsConstants.RIDE_SERVICE_CLASS);
    private static final Integer MAXIMUM_WAITING_TIME = 5;
    private static final Integer MINIMUM_BOOKING_HOUR = 3;

    /**
     * <p>
     *   Implement this method to get the Dto object
     *   and convert to entity object through mapper class
     *   then send the entity to repository layer to store ride
     *   in database
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be created
     * @return {@link Integer} created ride id
     *
     */
    private Ride createRide(RideDto rideDto) {
        Ride ride = rideRepository.save(RideMapper.convertRideDtoIntoRide(rideDto));
        logger.info(HelloCabsConstants.RIDE_CREATED + ride.getId());
        return ride;
    }

    /**
     * <p>
     *   Implement this method to fetch a particular ride using id
     *   if ride exists return ride else throw not found exception
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link RideDto} searched ride object
     *
     */
    public RideDto searchRideById(Integer id) {
        Ride ride = rideRepository.findById(id).orElse(null);

        if (ride != null) {
            logger.info(HelloCabsConstants.RIDE_FOUND + ride);
            return RideMapper.convertRideIntoRideDto(ride);
        }
        logger.error(HelloCabsConstants.RIDE_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_FOUND);
    }

    /**
     * <p>
     *   Implement this method to fetch a particular ride using id
     *   if ride exists return ride else throw not found exception
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link Ride} searched ride object
     *
     */
    public Ride fetchRideById(Integer id) {
        Ride ride = rideRepository.findById(id).orElse(null);

        if (ride != null) {
            logger.info(HelloCabsConstants.RIDE_FOUND + ride);
            return ride;
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
        return rideRepository.findAll().stream().map(RideMapper::convertRideIntoRideDto)
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
        Ride ride = rideRepository.save(RideMapper.convertRideDtoIntoRide(rideDto));
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
     * @param rideId {@link Integer} feedback to be updated to ride
     * @param reasonDto {@link ReasonDto} feedback and ride status details
     * @return {@link String} reason for ride cancellation
     *
     */
    public String deleteRide(Integer rideId, ReasonDto reasonDto) {
        Ride ride = fetchRideById(rideId);

        if (!Status.DROPPED.toString().equalsIgnoreCase(ride.getRideStatus())) {

            if (!Status.CANCELLED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                ride.setRideStatus(HelloCabsConstants.RIDE_IGNORED);
                ride.setFeedback(reasonDto.getReason());
                rideRepository.save(ride);
                logger.info(Status.CANCELLED);
                return HelloCabsConstants.RIDE_CANCELLED;
            }
            throw new HelloCabsException(HelloCabsConstants.RIDE_CANCELLED_ALREADY);
        }
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_CANCELLED);
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
        LocationDto locationDto = new LocationDto();
        locationDto.setId(bookDto.getPickupLocation());
        rideDto.setPickupLocation(locationDto);
        LocationDto locationDto1 = new LocationDto();
        locationDto1.setId(bookDto.getDropLocation());
        rideDto.setDropLocation(locationDto1);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(bookDto.getCustomerId());
        rideDto.setCustomerDto(customerDto);
        rideDto.setRideStatus(HelloCabsConstants.RIDE_BOOKED);
        rideDto.setRideBookedTime(LocalDateTime.now());
        return HelloCabsConstants.RIDE_CREATED + createRide(rideDto).getId()
                + HelloCabsConstants.WAITING_STATUS;
    }

    /**
     * <p>
     *   Implement this method often used to assign a ride to cab
     *   by passing the user input as an object and respective id
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} rideId to be confirmed
     * @param cabId {@link Integer} cabId to be assigned to pick the ride
     * @return {@link String} ride confirmation
     *
     */
    public String confirmRide(StatusDto statusDto, Integer rideId, Integer cabId) {
        Ride ride = fetchRideById(rideId);
        Cab cab = cabService.searchCabById(cabId);

        if (Status.ACCEPTED.toString().equalsIgnoreCase(statusDto.getRideStatus())
                && Status.AVAILABLE.toString().equalsIgnoreCase(cab.getCabStatus())) {

            if ((Status.BOOKED.toString().equalsIgnoreCase(ride.getRideStatus()))) {
                ride.setRideStatus(statusDto.getRideStatus());
                ride.setCab(cab);
                cab.setCabStatus(HelloCabsConstants.CAB_UNAVAILABLE);
                rideRepository.save(ride);
                cabService.updateCabDetailsById(cabId, cab);
                return HelloCabsConstants.CAB_ASSIGNED;
            }
            throw new HelloCabsException(HelloCabsConstants.RIDE_ACCEPTED_ALREADY);
        }
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_ACCEPTED);
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
    public CabDto updateRideStatus(StatusDto statusDto, Integer rideId) {
        Ride ride = fetchRideById(rideId);
        logger.info(HelloCabsConstants.RIDE_FOUND);
        return updateStatusInfo(statusDto, ride);
    }

    /**
     * <p>
     *   Implement this method is often used to change the status of both
     *   ride and cab by passing the rideStatus, and respective objects
     * </p>
     *
     * @param statusDto {@link StatusDto} status of ride
     * @param ride {@link Ride} change ride's ride status
     * @return {@link CabDto} updated cabDto
     *
     */
    private CabDto updateStatusInfo(StatusDto statusDto, Ride ride) {
        final String PICKED = "picked";
        final String DROPPED = "dropped";
        String rideStatus = statusDto.getRideStatus();
        Cab cab = ride.getCab();

        switch (rideStatus.toLowerCase()) {
            case PICKED:
                if (Status.ACCEPTED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                    ride.setRideStatus(statusDto.getRideStatus());
                    ride.setRidePickedTime(LocalDateTime.now());
                    ride.setRideStatus(rideStatus);
                    cab.setCabStatus(HelloCabsConstants.CAB_ON_RIDE);
                } else if (Status.PICKED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                    throw new HelloCabsException(HelloCabsConstants.RIDE_PICKED_ALREADY);
                } else {
                    throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_ACCEPTED);
                }
                break;

            case DROPPED:
                if (Status.PICKED.toString().equalsIgnoreCase(ride.getRideStatus())) {
                    ride.setRideDroppedTime(statusDto.getDropTime());

                    if (null == ride.getRideDroppedTime()) {
                        ride.setRideDroppedTime(LocalDateTime.now());
                    }
                    ride.setRideStatus(statusDto.getRideStatus());
                    cab.setCabStatus(HelloCabsConstants.CAB_AVAILABLE);
                    logger.info(ride.getDropLocation());
                    cab.setCurrentLocation(ride.getDropLocation().getLocationName());
                    Double price = calculateTravelFare(ride, cab.getCabCategory());
                    ride.setPrice(price);
                } else {
                    throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_PICKED);
                }
                break;

            default:
                throw new HelloCabsException(HelloCabsConstants.STATUS_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.STATUS_UPDATED + rideStatus);
        rideRepository.save(ride);
        return CabMapper.convertCabToCabDto(cabService.updateCabDetailsById(cab.getId(), cab));
    }

    /**
     * <p>
     *   Used this method whenever to give rating and
     *   feedback for the ride
     * </p>
     *
     * @param rideId {@link Integer} rating for the ride
     * @param ratingDto {@link RatingDto} get the feedback and
     *      rating for the ride when finished
     * @return {@link String} ride's feedback
     *
     */
    public String submitFeedBack(Integer rideId, RatingDto ratingDto) {
        Ride ride = fetchRideById(rideId);
        Cab cab = ride.getCab();

        if (Status.DROPPED.toString().equalsIgnoreCase(ride.getRideStatus())) {
            ride.setRating(ratingDto.getRating());
            ride.setFeedback(ratingDto.getFeedback());
            cabService.updateCabDetailsById(cab.getId(), (
                    calculateAverageRating(cab, ratingDto)));
            rideRepository.save(ride);
            return HelloCabsConstants.FEEDBACK_ADDED;
        }
        throw new HelloCabsException(HelloCabsConstants
                .CUSTOMER_NOT_DROPPED);
    }

    /**
     * <p>
     *     Method used to get the ride rating from user
     *     and calculate the average driver rating for the cab
     * </p>
     *
     * @param cab {@link Cab} cabDto in which rating has to be updated
     * @param ratingDto {@link RatingDto} ratingDto which has ride's rating
     * @return {@link Cab} updated driver rating
     *
     */
    private Cab calculateAverageRating(Cab cab, RatingDto ratingDto) {

        if (null != cab.getRides()) {
            List<Double> ratings = cab.getRides().stream()
                    .map(Ride :: getRating).toList();
            Double averageRating = ratings.stream().mapToDouble(
                    rating -> rating).summaryStatistics().getAverage();
            cab.setDriverRating(averageRating);
        } else {
            cab.setDriverRating(ratingDto.getRating());
        }
        return cab;
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
     * @param rideId {@link Integer}
     * @return ride {@link Ride} updated rideDto
     *
     */
    public String waitingToConfirmRide(Integer rideId) {
        Ride ride = fetchRideById(rideId);

        if (HelloCabsConstants.RIDE_BOOKED
                .equalsIgnoreCase(ride.getRideStatus())) {

            if ((MAXIMUM_WAITING_TIME < (LocalDateTime.now().getMinute())
                    - ride.getRideBookedTime().getMinute()) && (HelloCabsConstants.RIDE_BOOKED)
                    .equalsIgnoreCase(ride.getRideStatus())) {
                ride.setFeedback(HelloCabsConstants.CANCELLED_DUE_TO_UNAVAILABILITY);
                ride.setRideStatus(HelloCabsConstants.RIDE_IGNORED);
                rideRepository.save(ride);
                return HelloCabsConstants.CANCELLED_DUE_TO_UNAVAILABILITY;
            }
            logger.info(HelloCabsConstants.SEARCHING_CABS);
            return HelloCabsConstants.SEARCHING_CABS;
        }
        throw new HelloCabsException(HelloCabsConstants.ALREADY_ON_BOARD);
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
     * @param ride {@link Ride} rideDto
     * @param cabCategory {@link CabCategory} id to be searched
     * @return {@link Double}returns RidePrice by Time Of Travel
     *
     */
    private Double calculateTravelFare(Ride ride, CabCategory cabCategory) {

        if ((HelloCabsConstants.RIDE_COMPLETED).equalsIgnoreCase(ride.getRideStatus())) {
            Integer pickTime = ride.getRidePickedTime().getHour();
            Integer droppedTime = ride.getRideDroppedTime().getHour();
            Integer timeDifference = (droppedTime - pickTime);
            Double initialFare = cabCategory.getInitialFare();
            Double extraHourFare = cabCategory.getExtraFarePerHour();
            Double additionalFare = cabCategory.getPeakHourFare();
            boolean isPeakHour = (Integer.toString(pickTime)
                    .matches(HelloCabsConstants.PEAK_HOUR_REGEX));

            if (MINIMUM_BOOKING_HOUR > (timeDifference)) {
                return isPeakHour ? (initialFare + additionalFare) : initialFare;
            }
            Double fare = initialFare + ((timeDifference - MINIMUM_BOOKING_HOUR) * extraHourFare);
            return isPeakHour ? (fare + additionalFare) : fare;
        }
        logger.info(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
    }
}