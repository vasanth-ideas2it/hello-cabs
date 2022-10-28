package com.hellocabs.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.logger.LoggerConfiguration;
import com.hellocabs.model.Cab;
import com.hellocabs.model.CabCategory;
import com.hellocabs.service.CabService;
import com.hellocabs.service.RideService;

/**
 * <h> CabController </h>
 * <p>
 * Class is a Controller used to Access the cab details and
 * to perform the CRUD operations of cab object with the help
 * of RestController and RequestMapping annotations
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@RestController
@RequestMapping("cab")
public class CabController {

    public CabController(CabService cabService) {
        this.cabService = cabService;
    }
    private final CabService cabService;
    private RideService rideService;

    private Logger logger = LoggerConfiguration.getInstance("CabController.class");
    /**
     * <p>
     * Method used to post or create Cab details which is get from user as
     * a cabDto object and pass to cabService and return status to user
     * </p>
     * @param cabDto{@link RequestBody CabDto}valid object with required details
     * @return {@link String}returns the status of the given details if valid
     */
    @PostMapping("create")
    public String addCabDetails(@Valid @RequestBody CabDto cabDto) {

        return (null != cabDto) ? cabService.createCab(cabDto)
                         : " Failed :: Not Inserted ";
    }

    /**
     * <p>
     * Method used to put or update Cab details by using PostMapping with valid
     * cabDto object and pass to cab Service to update
     * </p>
     *
     * @param cabDto {@link @RequestBody CabDto}valid object with updated details
     * @return {@link String}returns the status of the given details
     */
    @PutMapping("updateCab")
    public String updateCabDetails(@Valid @RequestBody CabDto cabDto) {
        int id = cabDto.getId();
        return (null != cabDto) ? cabService.updateCabDetailsById(id,cabDto)
                                : " Failed :: Not Inserted ";
    }

    /**
     * <p>
     * Method used to show all cabDto Details list from service to user by
     * using GetMapping annotation
     * </p>
     *
     * @return {@link List<CabDto>}returns all the trainee details
     */
    @GetMapping("cabs")
    public List<CabDto> getAllCabsDetails()  {

        return cabService.showAllCabDetails();

    }

    /**
     * <p>
     * Method used to get  cab details  from server by using id
     * with help of GetMapping annotation if id exist returns respective
     * object or returns Record Not found
     * </p>
     * @param {@link int} id
     * @return {@link List<Cab>}returns cab details
     */
    @GetMapping("search/{id}")
    public CabDto getCabDetailsById(@PathVariable int id)  {

        return  cabService.displayCabDetailsById(id);

    }

    /**
     * <p>
     * Method used to delete details from server by using id
     * </p>
     * @param {@link int} id
     * @return {@link String>}returns Status of Cab details
     */
    @DeleteMapping("delete/{id}")
    public String deleteCabDetailsById(@PathVariable int id)  {

        return cabService.deleteCabDetailsById(id);
    }

    @PutMapping("update/{id}/{cabStatus}")
    public String updateCabStatus(@PathVariable int id, @PathVariable String cabStatus) {
        return cabService.updateCabStatus(id, cabStatus);
    }
}
