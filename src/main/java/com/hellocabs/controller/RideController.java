/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.BookDto;
import com.hellocabs.dto.FeedBackDto;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.RideService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

/**
 * <p>
 *   Implemented this class whenever a customer books a ride,
 *   to fetch particular ride, to show all ride history,
 *   to delete a ride while booking is not complete etc.,
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("ride")
public class RideController {

    private final RideService rideService;

    /**
     * <p>
     *   Used this method to fetch a particular ride
     *   by passing ride id to service
     * </p>
     *
     * @param id {@link Integer} ride's id to be searched
     * @return {@link ResponseEntity<Object>} searched ride object
     *
     */
    @GetMapping("search/{id}")
    public ResponseEntity<Object> searchRideById(@PathVariable Integer id) {
        return HelloCabsResponseHandler
                .generateResponse(HelloCabsConstants.RIDE_FOUND,
                HttpStatus.OK,rideService.searchRideById(id));
    }

    /**
     * <p>
     *   Used this method to retrieve all rides
     *   irrespective of id
     * </p>
     *
     * @return {@link ResponseEntity<Object>} set of all rides
     *
     */
    @GetMapping("rides")
    public ResponseEntity<Object> retrieveRides() {
        return HelloCabsResponseHandler.generateResponse(
                HelloCabsConstants.RIDE_FOUND,HttpStatus.FOUND,
                rideService.retrieveRides());
    }

    /**
     * <p>
     *   Used this method to update particular ride
     *   by inserting the values of fields and id
     *   to be updated
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link ResponseEntity<Object>} updated ride
     *
     */
    @PutMapping("update")
    public ResponseEntity<Object> updateRide(@Valid
            @RequestBody RideDto rideDto) {

        if (null != rideService.updateRide(rideDto)) {
            return HelloCabsResponseHandler.generateResponse(
                    HelloCabsConstants.RIDE_UPDATED,HttpStatus.OK, rideDto);
        }
        throw new HelloCabsException(HelloCabsConstants.RIDE_NOT_UPDATED);
    }

    /**
     * <p>
     *   Used this method whenever a customer books a ride
     *   and choose category by using category id and also
     *   ride details
     * </p>
     *
     * @param bookDto {@link BookDto} ride details of a customer
     * @return {@link ResponseEntity<Object>} booking id
     *
     */
    @PostMapping("book")
    public ResponseEntity<Object> bookRide(@Valid
            @RequestBody BookDto bookDto) {
        return HelloCabsResponseHandler
                .generateResponse(rideService.bookRide(bookDto),
                        HttpStatus.CREATED);
    }

    /**
     * <p>
     *   This method is often used to change the status of both ride and cab
     *   by passing the user input as an object
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @param rideId {@link Integer} rideId to be updated
     * @return {@link ResponseEntity<Object>} assigned cab details
     *
     */
    @PutMapping("status/{rideId}")
    public ResponseEntity<Object> updateRideStatus(@Valid
            @RequestBody StatusDto statusDto, @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(
                HelloCabsConstants.STATUS_UPDATED,HttpStatus.OK,
                rideService.updateRideStatus(statusDto, rideId));
    }

    /**
     * <p>
     *   This method is often used to assign a ride to cab
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
    @PutMapping("confirm/{rideId}/{cabId}")
    public ResponseEntity<Object> confirmRide(@Valid
            @RequestBody StatusDto statusDto, @PathVariable Integer rideId,
            @PathVariable Integer cabId) {
        return HelloCabsResponseHandler.generateResponse(
                rideService.confirmRide(statusDto, rideId, cabId),
                HttpStatus.OK);
    }


    /**
     * <p>
     *   This method is used to confirm the ride is booked
     *   if not accepted by cab for long time, ride will be
     *   cancelled automatically and shows user a message
     *   to choose another cab category
     * </p>
     *
     * @param rideId {@link Integer}
     * @return {@link ResponseEntity<Object>} statement on
     *         ride confirmation
     *
     */
    @GetMapping("waiting/{rideId}")
    public ResponseEntity<Object> waitingToConfirmRide(
            @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(
                rideService.waitingToConfirmRide(rideId), HttpStatus.OK);
    }

    /**
     * <p>
     *   Used this method whenever to give rating and
     *   feedback for the ride
     * </p>
     *
     * @param ratingDto {@link RatingDto} get the feedback and
     *      rating for the ride when finished
     * @param rideId {@link Integer} rating to be updated to ride
     * @return {@link ResponseEntity<Object>} ride's rating
     *
     */
    @PutMapping("rating/{rideId}")
    public ResponseEntity<Object> submitFeedback(@Valid @RequestBody
            RatingDto ratingDto, @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(
                HelloCabsConstants.RIDE_UPDATED, HttpStatus.OK,
                rideService.submitFeedBack(rideId, ratingDto));
    }

    /**
     * <p>
     *   Used this method whenever a user wants to cancel
     *   the ride for some reason
     * </p>
     *
     * @param feedBackDto {@link FeedBackDto} feedback of the ride
     * @param rideId {@link Integer} update rating for the ride
     * @return {@link ResponseEntity<Object>} ride cancellation
     *
     */
    @DeleteMapping("cancel/{rideId}")
    public ResponseEntity<Object> deleteRideById(@Valid @RequestBody
            FeedBackDto feedBackDto, @PathVariable Integer rideId) {
        return HelloCabsResponseHandler.generateResponse(
                rideService.deleteRide(rideId, feedBackDto),
                HttpStatus.OK);
    }
}
