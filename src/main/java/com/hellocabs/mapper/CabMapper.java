package com.hellocabs.mapper;

import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Ride;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CabMapper {

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
        List<RideDto> rideDtos= cabDto.getRides();
        List<Ride> rides;

        if (null != rideDtos) {
            rides = rideDtos.stream().map(RideMapper :: convertRideDtoIntoRide).toList();
            cab.setRides(rides);
        }
        return cab;
    }


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
        List<Ride> rides= cab.getRides();
        List<RideDto> rideDtos;

        if (null != rides) {
            rideDtos = rides.stream().map(RideMapper :: convertRideIntoRideDto).toList();
            cabDto.setRides(rideDtos);
        }
        return cabDto;
    }

}
