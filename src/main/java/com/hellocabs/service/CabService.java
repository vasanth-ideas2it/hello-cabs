package com.hellocabs.service;

import com.hellocabs.dto.CabDto;
import com.hellocabs.model.Cab;

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
     * Method used to create Cab  details by using CabDto Object
     * @param {@link CabDto}cabDto object with required details
     * @return {@link String}returns status of given Details
     */
    String createCab(CabDto cabDto);

    /**
     * Method used to update Cab  details by using CabDto Object
     * @param {@link CabDto}cabDto object with required details
     * @return {@link String}returns Status of the cab details
     */
    String updateCabDetailsById(int id, CabDto cabDto);

    /**
     * Method used to get Cab  details by using CabId
     *
     * @param {@link int}cabId
     * @return {@link Cab}returns cab details object by using cabId
     */
    CabDto displayCabDetailsById(int id);

    /**
     * Method used to Delete Cab  details by using CabId
     * @param {@link int}cabId
     *  @return {@link }
     */
    String deleteCabDetailsById(int id);

    /**
     * Method used to get All Cab details by using CabId
     * @param {noValue}
     *  @return {@link List<CabDto>}returns All Cab Details
     */
    List<CabDto> showAllCabDetails();
}
