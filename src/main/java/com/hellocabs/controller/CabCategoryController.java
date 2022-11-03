/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.controller;

import com.hellocabs.response.HelloCabsResponseHandler;
import org.apache.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.service.CabCategoryService;

@RestController
@RequestMapping("/cabcategory")
public class CabCategoryController {

    private Logger logger = LoggerConfiguration.getInstance("CabCategoryController.class");
    CabCategoryService cabCategoryService;

    CabCategoryController(CabCategoryService cabCategoryService) {
        this.cabCategoryService = cabCategoryService;
    }

    /**
     * <p>
     * This method is to add cab category Details if the
     * location is not already exist
     * </p>
     *
     * @param cabCategoryDto
     *        for which the cab category to be added is given
     * @return ResponseEntity<Object>
     *         inserted cab category id is returned as message with http status
     */
    @PostMapping("/create")
    private ResponseEntity<Object> addCabCategory(@Valid @RequestBody CabCategoryDto cabCategoryDto) {
        String cabCreated = cabCategoryService.createCabCategory(cabCategoryDto);
        logger.info(cabCreated);
        return HelloCabsResponseHandler.generateResponse(cabCreated, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is to search cab category Details.
     * </p>
     *
     * @param id
     *        for which the id of the cab category need to
     *        be searched is given
     * @return CabCategoryDto
     *         searched cab category is returned
     */
    @GetMapping("/search/{id}")
    private ResponseEntity<Object> searchCabCategoryById(@PathVariable Integer id) {
        CabCategoryDto cabCategoryDto = cabCategoryService.getCabCategoryById(id);

        if ( null == cabCategoryDto) {
            logger.info(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.CAB_CATEGORY_FOUND);
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .CAB_CATEGORY_FOUND, HttpStatus.FOUND, cabCategoryDto);
    }

    /**
     * <p>
     * This method is to update cab category Details.
     * </p>
     *
     * @param cabCategoryDto
     *        for which the cab category to be updated is given
     * @return CabCategoryDto
     *         updated cab category is returned
     */
    @PutMapping("/update/{id}")
    private ResponseEntity<Object> updateCabCategory(@PathVariable int id, @Valid @RequestBody CabCategoryDto cabCategoryDto) {
        CabCategoryDto updatedCabCategoryDto = cabCategoryService.updateCabCategory(id, cabCategoryDto);

        if (null == updatedCabCategoryDto) {
            logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        }
        logger.error(HelloCabsConstants.CAB_CATEGORY_UPDATED);
        return  HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .CAB_CATEGORY_UPDATED, HttpStatus.OK, updatedCabCategoryDto);
    }

    /**
     * <p>
     * This method is to delete cab category Details.
     * </p>
     *
     * @param id
     *        for which the id of the cab category need to
     *        be deleted is given
     * @return String
     *         gets the message whether the cab category is
     *         deleted or not
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteCabCategoryById(@PathVariable Integer id) {
        if (cabCategoryService.deleteCabCategoryById(id)) {
            logger.info(HelloCabsConstants.CAB_CATEGORY_DELETED);
            return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                    .CAB_CATEGORY_DELETED, HttpStatus.OK);
        } else {
            logger.error(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.CAB_CATEGORY_NOT_FOUND);
        }
    }

    /**
     * <p>
     * This method is to display all cab category Details.
     * </p>
     *
     * @return List<CabCategoryDto>
     *         retrieves all the cab categories
     */
    @GetMapping("/cabcategories")
    private ResponseEntity<Object> getAllCabCategories() {
        List<CabCategoryDto> cabCategoryDtos = cabCategoryService.retrieveAllCabCategories();
        logger.info(HelloCabsConstants.CAB_CATEGORY_FOUND);
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .CAB_CATEGORY_FOUND, HttpStatus.OK, cabCategoryDtos);
    }
}
