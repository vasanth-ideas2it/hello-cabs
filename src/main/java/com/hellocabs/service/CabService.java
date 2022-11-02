/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service;

import com.hellocabs.dto.CabDto;

import java.util.List;
/**
 * <h> CabService </h>
 * <p>
 * Interface is used to implement the abstract methods for the cab details
 *
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
public interface CabService {

    /**
     * <p>
     * Method used to create Cab  details by using CabDto Object,
     * convert to cab object then pass to database to create
     * new cab id to store in it
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @return {@link String}returns cab details object
     */
    String createCab(CabDto cabDto);

    /**
     * <p>
     * Method used to update Cab  details by using CabDto Object
     * and id to check and update the existing cab details
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @param id {@link Integer}used to check the exists details in database
     * @return {@link String}returns status of the cab details
     */
    String updateCabDetailsById(Integer id, CabDto cabDto);

    /**
     * <p>
     * Method used to get Cab  details by using CabId if exists
     * or returns null to controller
     *</p>
     *
     * @param id{@link Integer}used to get details from server if exists
     * @return {Cab}returns cab details object by using cabId
     */
    CabDto displayCabDetailsById(Integer id);

    /**
     * <p>
     * Method used to Delete Cab details by using CabId
     * if it exists returns the status of the cabId or returns the id not found
     * </p>
     *
     * @param id{@link Integer}used to delete details if exists
     *  @return {String}returns Status of the cab id
     */
    String deleteCabDetailsById(Integer id);

    String verifyCabDetails(CabDto cabDto);

    /**
     * <p>
     * Method used to get All Cab details  from server which is
     * implemented from EmployeeService and pass to the controller
     * </p>
     *
     * @return {List<CabDto>}returns all the cab details in the form of list
     */
    List<CabDto> showAllCabDetails();

    /**
     * <p>
     * Method used to change the cab status with the help of
     * cabId and updated cabStatus to update in cabDetails
     * </p>
     *
     * @param id{@link Integer}used to check and get the respective cab object
     * @param cabStatus{@link String}used to get updated status and pass to respective cab
     * @return {String}returns the status of the cab status
     */
    String updateCabStatus(Integer id, String cabStatus);
}
