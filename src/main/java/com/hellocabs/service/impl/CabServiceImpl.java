/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.CabMapper;
import com.hellocabs.model.Cab;
import com.hellocabs.repository.CabRepository;
import com.hellocabs.service.CabService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * <h> CabServiceImpl </h>
 * <p>
 * Class is used to define the implemented methods from CabService interface
 * and used to maintain the cab Details given by the user with help of cabDto object
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@Service
public class CabServiceImpl implements CabService {

    private static final Logger logger = LoggerConfiguration.getInstance("CabServiceImpl.class");
    private final CabRepository cabRepository;

    public CabServiceImpl(CabRepository cabRepository) {
        this.cabRepository = cabRepository;
    }


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
    @Override
    public String createCab(CabDto cabDto) {
        Cab cab = CabMapper.convertCabDtoToCab(cabDto);
        if (null != cab) {
            Cab cabDetails = cabRepository.save(cab);
            Integer id = cabDetails.getId();
            logger.info(HelloCabsConstants. CAB_CREATED + cab.getId());
            return (HelloCabsConstants.CAB_CREATED + " " + id);

        }
        logger.info(HelloCabsConstants.CAB_NOT_CREATED);
        return HelloCabsConstants.CAB_NOT_CREATED;
    }

    /**
     * <p>
     * Method used to update Cab  details by using CabDto Object
     * and id to check and update the existing cab details
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @Param id {@link Integer}used to check the exists details in database
     * @return {@link String}returns status of the cab details
     */
    @Override
    public String updateCabDetailsById(Integer id, CabDto cabDto) {

        Cab cab = CabMapper.convertPartialCabDtoIntoCab(cabDto);

        if (cabRepository.existsById(id)) {
            Cab cabDetails = cabRepository.save(cab);
            logger.info(HelloCabsConstants.CAB_UPDATED + cabDetails.getId());
           return HelloCabsConstants.CAB_UPDATED;
        }
        logger.info(HelloCabsConstants.CAB_NOT_UPDATED);
        return HelloCabsConstants.CAB_NOT_UPDATED;
    }

    /**
     * <p>
     * Method used to get Cab  details by using CabId if exists
     * or returns null to controller
     *</p>
     *
     * @param id{@link Integer}used to get details from server if exists
     * @return {Cab}returns cab details object by using cabId
     */
    @Override
    public CabDto displayCabDetailsById(Integer id) {

        if (cabRepository.existsById(id)) {
            Cab cab = cabRepository.findByIdAndIsActive(id,false);
            logger.info(HelloCabsConstants.CAB_FOUND + cab);
            //return CabMapper.convertCabToCabDto(cab);
            return CabMapper.convertPartialCabIntoCabDto(cab);
        }
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     * Method used to Delete Cab details by using CabId
     * if it exists returns the status of the cabId or returns the id not found
     * </p>
     *
     * @param id{@link Integer}used to delete details if exists
     *  @return {String}returns Status of the cab id
     */
    @Override
    public String deleteCabDetailsById(Integer id) {

        if (cabRepository.existsById(id)) {
            Cab cab = cabRepository.findByIdAndIsActive(id,false);
            cab.setActive(true);
            cabRepository.save(cab);
            logger.info(HelloCabsConstants.CAB_DELETED + cab.getId());
            return HelloCabsConstants.CAB_DELETED;
        }
        logger.error(HelloCabsConstants.CAB_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.CAB_NOT_FOUND);
    }

    /**
     * <p>
     * Method used to verify Cab details by using CabId
     * if it exists returns the status of the cabId or returns the id not found
     * </p>
     *
     * @param cabDto{@link CabDto}used to verify details if exists
     *  @return {String}returns Status of the login
     */
    @Override
    public String verifyCabDetails(CabDto cabDto) {
        Cab cab = CabMapper.convertCabDtoToCab(cabDto);
        Long number = cab.getMobileNumber();
        String password = cab.getPassword();
        Cab cabDetails =  cabRepository.findByMobileNumberAndPassword(number,password);
        if(null != cabDetails) {
            return  HelloCabsConstants.LOGIN_SUCCESSFUL;
        }
        return  HelloCabsConstants.LOGIN_NOT_SUCCESSFUL;
    }

    /**
     * <p>
     * Method used to get All Cab details  from server which is
     * implemented from EmployeeService and pass to the controller
     * </p>
     *
     * @return {List<CabDto>}returns all the cab details in the form of list
     */
    @Override
    public List<CabDto> showAllCabDetails() {
         return cabRepository.findAllByIsActive(false).stream().map(CabMapper :: convertCabToCabDto).collect(Collectors.toList());
    }

    /**
     * <p>
     * Method used to change the cab status with the help of
     * cabId and updated cabStatus to update in cabDetails
     * </p>
     *
     * @param id{@link Integer}used to check and get the respective cab object
     * @Param cabStatus{@link String}used to get updated status and pass to respective cab
     * @return {String}returns the status of the cab status
     */
    @Override
    public String updateCabStatus(Integer id, String cabStatus) {

        if (cabRepository.existsById(id)) {
            CabDto cabDto = displayCabDetailsById(id);
            cabDto.setCabStatus(cabStatus);
            return updateCabDetailsById(id,cabDto);
        }
        return HelloCabsConstants.CAB_NOT_FOUND;
    }


}
