package com.hellocabs.mapper;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Cab;
import com.hellocabs.model.CabCategory;
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
    public static Cab convertCabDtoToCab(CabDto cabDto) {

        Cab cab = new Cab();
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
        Set<RideDto> rideDtos= cabDto.getRides();
        Set<Ride> rides;

        if (null != rideDtos) {
            rides = rideDtos.stream().map(RideMapper :: convertRideDtoIntoRide).collect(Collectors.toSet());
            cab.setRides(rides);
        }
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
    public static CabDto convertCabToCabDto(Cab cab) {

        CabDto cabDto = new CabDto();
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
        Set<Ride> rides= cab.getRides();
        Set<RideDto> rideDtos;

        if (null != rides) {
            rideDtos = rides.stream().map(RideMapper :: convertRideIntoRideDto).collect(Collectors.toSet());
            cabDto.setRides(rideDtos);
        }
        return cabDto;
    }

}
