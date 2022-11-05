/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.response.HelloCabsResponseHandler;

import lombok.RequiredArgsConstructor;

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

import javax.validation.Valid;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.service.CabCategoryService;

@RestController
@RequestMapping("/cabcategory")
@RequiredArgsConstructor
public class CabCategoryController {

    private final CabCategoryService cabCategoryService;

    /**
     * <p>
     *   This method is to add cab category Details if the
     *   location is not already exist
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto}
     *        for which the cab category to be added is given
     * @return {@link ResponseEntity<Object>}
     *         inserted cab category id is returned as message with http status
     *
     */
    @PostMapping("/create")
    private ResponseEntity<Object> addCabCategory(@Valid @RequestBody CabCategoryDto cabCategoryDto) {
        return HelloCabsResponseHandler.generateResponse(cabCategoryService
                .createCabCategory(cabCategoryDto), HttpStatus.OK);
    }

    /**
     * <p>
     *   This method is to search cab category Details using id.
     * </p>
     *
     * @param id {@link Integer}
     *        for which the id of the cab category need to
     *        be searched is given
     * @return {@link ResponseEntity<Object>}
     *         searched cab category is returned
     *
     */
    @GetMapping("/search/{id}")
    private ResponseEntity<Object> searchCabCategoryById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .CAB_CATEGORY_FOUND, HttpStatus.FOUND, cabCategoryService.getCabCategoryById(id));
    }

    /**
     * <p>
     *   This method is to update cab category Details.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto}
     *        for which the cab category to be updated is given
     * @return {@link ResponseEntity<Object>}
     *         updated cab category is returned
     */
    @PutMapping("/update")
    private ResponseEntity<Object> updateCabCategory( @Valid @RequestBody CabCategoryDto cabCategoryDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CAB_CATEGORY_UPDATED,
                HttpStatus.OK, cabCategoryService.updateCabCategory(cabCategoryDto));
    }

    /**
     * <p>
     *   This method is to delete cab category Details.
     * </p>
     *
     * @param id {@link Integer} for which the id of the cab category need to
     *        be deleted is given
     * @return {@link ResponseEntity<Object>} gets the message whether the
     *         cab category is deleted or not
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteCabCategoryById(@PathVariable Integer id) {
            return HelloCabsResponseHandler.generateResponse(cabCategoryService.deleteCabCategoryById(id), HttpStatus.OK);
    }

    /**
     * <p>
     *   This method is to display all cab category Details.
     * </p>
     *
     * @return {@link ResponseEntity<Object>} retrieves all the cab categories
     */
    @GetMapping("/cabcategories")
    private ResponseEntity<Object> getAllCabCategories() {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .CAB_CATEGORY_FOUND, HttpStatus.OK, cabCategoryService.retrieveAllCabCategories());
    }
}
