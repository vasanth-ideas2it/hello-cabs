package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Ride;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h> CabMapper </h>
 * <p>
 * Class is used to convert the cabDto object to cab object and
 * cab object to cabDto object respectively and returns to respected classes
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
public class CabMapper {

    /**
     * <p>
     * Method used to change the cabDto object into cab with the help
     * of cab entity object and return to respective method
     * </p>
     *
     * @param cabDto{@link CabDto}get valid information from user
     * @return {Cab}returns the cab object
     */
    public static Cab convertPartialCabDtoIntoCab(CabDto cabDto) {
        Cab cab = new Cab();
        cab.setId(cabDto.getId());
        cab.setCabNumber(cabDto.getCabNumber());
        cab.setCabStatus(cabDto.getCabStatus());
        cab.setCarModel(cabDto.getCarModel());
        cab.setCurrentLocation(cabDto.getCurrentLocation());
        cab.setDriverName(cabDto.getDriverName());
        cab.setDriverRating(cabDto.getDriverRating());
        cab.setEmail(cabDto.getEmail());
        cab.setGender(cabDto.getGender());
        cab.setId(cabDto.getId());
        cab.setLicenseNumber(cabDto.getLicenseNumber());
        cab.setMobileNumber(cabDto.getMobileNumber());
        cab.setPassword(cabDto.getPassword());
        return cab;
    }

    /**
     * <p>
     * Method used to change the cab object into cabDto with the help
     * of cabDto entity object and return to respective method
     * </p>
     *
     * @param cab{@link Cab}get valid information from user
     * @return {CabDto}returns the cabDto object
     */
    public static CabDto convertPartialCabIntoCabDto(Cab cab) {

        CabDto cabDto = new CabDto();
        cabDto.setId(cab.getId());
        cabDto.setCabNumber(cab.getCabNumber());
        cabDto.setCabStatus(cab.getCabStatus());
        cabDto.setCarModel(cab.getCarModel());
        cabDto.setDriverName(cab.getDriverName());
        cabDto.setDriverRating(cab.getDriverRating());
        cabDto.setGender(cab.getGender());
        cabDto.setEmail(cab.getEmail());
        cabDto.setId(cab.getId());
        cabDto.setCurrentLocation(cab.getCurrentLocation());
        cabDto.setLicenseNumber(cab.getLicenseNumber());
        cabDto.setMobileNumber(cab.getMobileNumber());
        cabDto.setPassword(cab.getPassword());
        cabDto.setActive(cab.isActive());
        return cabDto;
    }

    /**
     * <p>
     * Method used to change the cab object into cabDto with the help
     * of cabDto entity object and return to respective method
     * </p>
     *
     * @param cab{@link Cab}get valid information from user
     * @return {CabDto}returns the cabDto object
     */
    public static CabDto convertCabToCabDto(Cab cab) {
        return MapperConfig.getConfigure().map(cab, CabDto.class);

    }

    /**
     * <p>
     * Method used to change the cabDto object into cab with the help
     * of cab entity object and return to respective method
     * </p>
     *
     * @param cabDto{@link CabDto}get valid information from user
     * @return {Cab}returns the cab object
     */
    public static Cab convertCabDtoToCab(CabDto cabDto) {
        return MapperConfig.getConfigure().map(cabDto, Cab.class);
    }
}
