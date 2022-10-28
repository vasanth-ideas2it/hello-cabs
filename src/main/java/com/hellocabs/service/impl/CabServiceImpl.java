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
 * Class is used to define the implemented methods from CabService interface
 * and used to maintain the cab Details given by the user with help opf cabDto object
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
        String message = "Failed :: Not Inserted";
        if (null != cab) {
            Cab cabDetails = cabRepository.save(cab);
            int id = cabDetails.getId();
            message = "Successfully :: The CabId :" + id + " Inserted ";
        }
        return message;
    }

    /**
     * <p>
     * Method used to update Cab  details by using CabDto Object
     * and id to check and update the existing cab details
     * </p>
     *
     * @param cabDto {@link CabDto} object with required details
     * @Param id {@link int}used to check the exists details in database
     * @return {@link String}returns status of the cab details
     */
    @Override
    public String updateCabDetailsById(int id,CabDto cabDto) {

        Cab cab = CabMapper.convertCabDtoToCab(cabDto);
        String message = "Failed :: Not Updated";

        if (cabRepository.existsById(id)) {
            Cab cabDetails = cabRepository.save(cab);
            int cabId = cabDetails.getId();
            message = "Successfully :: The CabId :" + cabId + " Updated ";
        }
        return message;
    }

    /**
     * <p>
     * Method used to get Cab  details by using CabId if exists
     * or returns null to controller
     *</p>
     *
     * @param id{@link int}used to get details from server if exists
     * @return {Cab}returns cab details object by using cabId
     */
    @Override
    public CabDto displayCabDetailsById(int id) {
        CabDto cabDto = null;
        if (cabRepository.existsById(id)) {
            Cab cab = cabRepository.findById(id).orElse(null);
            return CabMapper.convertCabToCabDto(cab);
        }
        return new CabDto();
    }

    /**
     * <p>
     * Method used to Delete Cab details by using CabId
     * if it exists returns the status of the cabId or returns the id not found
     * </p>
     *
     * @param id{@link int}used to delete details if exists
     *  @return {String}returns Status of the cab id
     */
    @Override
    public String deleteCabDetailsById(int id) {
        String message = "Failed :: The ID :" + id + " NO Record in Database ";
        if (cabRepository.existsById(id)) {
            cabRepository.deleteById(id);
            return "Successfully :: The ID : " + id + " Deleted ";
        }
        return message;
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
         return cabRepository.findAll().stream().map(CabMapper :: convertCabToCabDto).collect(Collectors.toList());
    }

    /**
     * <p>
     * Method used to change the cab status with the help of
     * cabId and updated cabStatus to update in cabDetails
     * </p>
     *
     * @param id{@link int}used to check and get the respective cab object
     * @Param cabStatus{@link String}used to get updated status and pass to respective cab
     * @return {String}returns the status of the cab status
     */
    @Override
    public String updateCabStatus(int id, String cabStatus) {

        String message = " Failed :: THERE IS NO CAB ID To Update ";
        if (cabRepository.existsById(id)) {
            CabDto cabDto = displayCabDetailsById(id);
            cabDto.setCabStatus(cabStatus);
            message = updateCabDetailsById(id,cabDto);
            return message + "\n CabStatus : " + cabStatus + " Status Updated Successfully ";
        }
        return message;
    }

}
