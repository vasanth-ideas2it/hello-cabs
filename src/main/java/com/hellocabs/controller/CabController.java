package com.hellocabs.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hellocabs.dto.CabDto;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.model.Cab;
import com.hellocabs.service.CabService;

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

    private Logger logger = LoggerConfiguration.getInstance("CabController.class");
    /**
     * <p>
     * Method used to post or create Cab details which is get from user as
     * a cabDto object and pass to cabService and return status to user
     * </p>
     *
     * @param cabDto{@link RequestBody CabDto}valid object with required details
     * @return {@link String}returns the status of the given details if valid
     */
    @PostMapping("create")
    private String addCabDetails(@Valid @RequestBody CabDto cabDto) {

        return  cabService.createCab(cabDto);
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
    private String updateCabDetails(@Valid @RequestBody CabDto cabDto) {
        int id = cabDto.getId();
        return cabService.updateCabDetailsById(id,cabDto);
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
    private List<CabDto> getAllCabsDetails()  {

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
    private CabDto getCabDetailsById(@PathVariable int id)  {

        return  cabService.displayCabDetailsById(id);

    }

    /**
     * <p>
     * Method used to delete details from server by using id with help
     * of DeleteMapping and pathVariable
     * </p>
     * @param {@link int} id
     * @return {@link String>}returns Status of Cab details
     */
    @DeleteMapping("delete/{id}")
    private String deleteCabDetailsById(@PathVariable int id)  {

        return cabService.deleteCabDetailsById(id);
    }

    /**
     * <p>
     * Method used to put or update Cab details by using PostMapping with valid
     * cabDto object and pass to cab Service to update
     * </p>
     *
     * @param id {@link int}to check respective cab or not
     * @param cabStatus{@link String}to change or update the status of cab
     * @return {@link String}returns the status of the given details
     */
    @PutMapping("update/{id}/{cabStatus}")
    private String updateCabStatus(@PathVariable int id, @PathVariable String cabStatus) {
        return cabService.updateCabStatus(id, cabStatus);
    }
}
