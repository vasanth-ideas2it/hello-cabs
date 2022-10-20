package com.hellocabs.mapper;

import com.hellocabs.dto.CabDto;
import com.hellocabs.model.Cab;

import java.util.List;
import java.util.stream.Collectors;

public class CabMapper {

    public static Cab convertCabDtoToCab(CabDto cabDto) {

        Cab cab = new Cab();
        cab.setCabNumber(cabDto.getCabNumber());
        cab.setCabStatus(cabDto.getCabStatus());
        cab.setCabCategories(cabDto.getCabCategories());
        cab.setCarModel(cabDto.getCarModel());
        cab.setCurrentLocation(cabDto.getCurrentLocation());
        cab.setDriverName(cabDto.getDriverName());
        cab.setDriverRating(cabDto.getDriverRating());
        cab.setEmail(cabDto.getEmail());
        cab.setGender(cabDto.getGender());
        cab.setId(cabDto.getId());
        cab.setLicenseNumber(cabDto.getLicenseNumber());
        cab.setMobileNumber(cabDto.getMobileNumber());
        cab.setRides(cabDto.getRides());
        return cab;
    }
    public static CabDto convertCabToCabDto(Cab cab) {

        CabDto cabDto = new CabDto();
        cabDto.setCabNumber(cabDto.getCabNumber());
        cabDto.setCabCategories(cab.getCabCategories());
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
        cabDto.setRides(cab.getRides());
        return cabDto;
    }

}
