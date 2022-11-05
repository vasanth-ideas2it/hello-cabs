/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabDto;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.CabService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h> CabController </h>
 * <p>
 *   Class is a Controller used to Access the cab details and
 *   to perform the CRUD operations of cab object with the help
 *   of RestController and RequestMapping annotations
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("cab")
public class CabController {

    private final CabService cabService;

    /**
     * <p>
     *   Method used to post or create Cab details which is get from user as
     *   a cabDto object and pass to cabService and return status to user
     * </p>
     *
     * @param cabDto {@link RequestBody CabDto}valid object with required details
     * @return {@link ResponseEntity<Object>} the status of the given details if valid
     *
     */
    @PostMapping("create")
    private ResponseEntity<Object> addCabDetails(@Valid @RequestBody CabDto cabDto) {
        return  HelloCabsResponseHandler
                .generateResponse(cabService.createCab(cabDto), HttpStatus.CREATED);
    }

    /**
     * <p>
     *   Method used to put or update Cab details by using PutMapping with valid
     *   cabDto object and pass to cab Service to update
     * </p>
     *
     * @param cabDto {@link @RequestBody CabDto}valid object with updated details
     * @return {@link ResponseEntity<Object>}returns the status of the given details
     *
     */
    @PutMapping("updateCab")
    private ResponseEntity<Object> updateCabDetails(@Valid @RequestBody CabDto cabDto) {
        return HelloCabsResponseHandler
                .generateResponse(cabService.updateCabDetailsById(cabDto.getId(),cabDto), HttpStatus.OK);
    }

    /**
     * <p>
     *   Method used to show all cabDto Details list from service to user by
     *   using GetMapping annotation
     * </p>
     *
     * @return {@link ResponseEntity<Object>}returns all the trainee details
     *
     */
    @GetMapping("cabs")
    private ResponseEntity<Object> getAllCabsDetails()  {
        return HelloCabsResponseHandler
                .generateResponse(HelloCabsConstants.CAB_AVAILABLE,
                        HttpStatus.FOUND, cabService.showAllCabDetails());

    }

    /**
     * <p>
     *   Method used to get  cab details  from server by using id
     *   with help of GetMapping annotation if id exist returns respective
     *   object or returns Record Not found
     * </p>
     *
     * @param id {@link Integer}to check the respective Cab Object or not
     * @return {@link ResponseEntity<Object>}returns respective cab details
     *
     */
    @GetMapping("search/{id}")
    private ResponseEntity<Object> getCabDetailsById(@PathVariable Integer id)  {
        return HelloCabsResponseHandler
                .generateResponse(HelloCabsConstants.CAB_AVAILABLE,
                        HttpStatus.FOUND, cabService.displayCabDetailsById(id));

    }

    /**
     * <p>
     *   Method used to delete details from server by using id with help
     *   of DeleteMapping and pathVariable
     * </p>
     *
     * @param id {@link Integer} cab id to be deleted
     * @return {@link ResponseEntity<Object>>}returns Status of Cab details
     *
     */
    @DeleteMapping("delete/{id}")
    private ResponseEntity<Object> deleteCabDetailsById(@PathVariable Integer id)  {
        return HelloCabsResponseHandler
                .generateResponse(cabService.deleteCabDetailsById(id), HttpStatus.OK);
    }
}
