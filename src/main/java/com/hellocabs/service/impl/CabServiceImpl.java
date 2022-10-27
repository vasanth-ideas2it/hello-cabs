package com.hellocabs.service.impl;

import com.hellocabs.dto.CabDto;
import com.hellocabs.mapper.CabMapper;
import com.hellocabs.model.Cab;
import com.hellocabs.repository.CabRepository;
import com.hellocabs.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * <h> CabServiceImpl </h>
 * <p>
 * Class is used to define the implemented 
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@Service
public class CabServiceImpl implements CabService {

    public CabServiceImpl(CabRepository cabRepository) {
        this.cabRepository = cabRepository;
    }

    CabRepository cabRepository;

    /**
     * Method used to create Cab  details by using CabDto Object
     * @param {@link CabDto}cabDto object with required details
     * @return {@link String}returns cab details object
     */
    @Override
    public String createCab(CabDto cabDto) {
        Cab cab = CabMapper.convertCabDtoToCab(cabDto);
        String message = "Failed :: Not Inserted";
        if (null != cab) {
            Cab cabDetails = cabRepository.save(cab);
            int id = cabDetails.getId();
            message = "Successfully :: The CabId :" + id + " Inserted ";
        }
        return message;
    }

    /**
     * Method used to update Cab  details by using CabDto Object
     * @param {@link CabDto}cabDto object with required details
     * @return {@link String}returns status of the cab details
     */
    @Override
    public String updateCabDetailsById(int id,CabDto cabDto) {

        Cab cab = CabMapper.convertCabDtoToCab(cabDto);
        String message = "Failed :: Not Updated";

        if (null != cab) {
            Cab cabDetails = cabRepository.save(cab);
            int cabId = cabDetails.getId();
            message = "Successfully :: The CabId :" + cabId + " Updated ";
        }
        return message;
    }

    /**
     * Method used to get Cab  details by using CabId
     *
     * @param {@link int}cabId
     * @return {Cab}returns cab details object by using cabId
     */
    @Override
    public CabDto displayCabDetailsById(int id) {
        CabDto cabDto = null;
        if (cabRepository.existsById(id)) {
            Cab cab = cabRepository.findById(id).orElse(null);
            return CabMapper.convertCabToCabDto(cab);
        }
        return cabDto;
    }

    /**
     * Method used to Delete Cab  details by using CabId
     * @param {@link int}cabId
     *  @return {String}returns Status of the cab id
     */
    @Override
    public String deleteCabDetailsById(int id) {
        String message = "Failed :: The ID :" + id + " Not Deleted ";
        if (cabRepository.existsById(id)) {
            cabRepository.deleteById(id);
            return "Successfully :: The ID : " + id + " Deleted ";
        }
        return message;
    }

    /**
     * Method used to get All Cab details  from server which is
     * implemented from EmployeeService
     *
     * @return {List<CabDto>}returns all the cab details
     */
    @Override
    public List<CabDto> showAllCabDetails() {
         return cabRepository.findAll().stream().map(CabMapper :: convertCabToCabDto).collect(Collectors.toList());
    }

    @Override
    public String updateCabStatus(int id, String cabStatus) {
        return null;
    }

}
