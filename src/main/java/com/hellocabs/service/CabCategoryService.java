/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service;

import com.hellocabs.dto.CabCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CabCategoryService {
    /**
     * <p>
     * This method is to add cab category Details.
     * </p>
     *
     * @param cabCategoryDto {@link CabCategoryDto} for which the cab category to be added is given
     * @return {@link String} if cab category inserted cab category id is returned with message
     *         otherwise returns only not inserted message
     *         
     */
    String createCabCategory(CabCategoryDto cabCategoryDto);

    /**
     * <p>
     * This method is to update location Details.
     * </p>
     *
     * @param cabCategoryDto
     *        for which the location to be updated is given
     * @return CabCategoryDto
     *         updated location is returned
     */
    CabCategoryDto updateCabCategory(CabCategoryDto cabCategoryDto);

    /**
     * <p>
     * This method is to search location Details.
     * </p>
     *
     * @param id
     *        for which the id of the location need to
     *        be searched is given
     * @return CabCategoryDto
     *         searched location is returned if present
     *         otherwise null
     */
    CabCategoryDto getCabCategoryById(Integer id);

    /**
     * <p>
     * This method is to delete location Details.
     * </p>
     *
     * @param id
     *        for which the id of the cab category need to
     *        be deleted is given
     * @return String
     *         returns message if the cab category of given id is deleted
     *         otherwise throws exception
     */
    String deleteCabCategoryById(Integer id);

    /**
     * <p>
     * This method is to display all location Details.
     * </p>
     *
     * @return List<CabCategoryDto>
     *         retrieves all the cab categories
     */
    List<CabCategoryDto> retrieveAllCabCategories();
}
