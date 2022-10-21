package com.hellocabs.controller;

import com.hellocabs.dto.CabDto;
import com.hellocabs.model.Cab;
import com.hellocabs.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h> CabController </h>
 * <p>
 * Class is used to Access or Controller the cab details and
 * to perform the CRUD operations of cab details
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@RestController
@Validated
@RequestMapping("cab")
public class CabController {

    public CabController(CabService cabService) {
        this.cabService = cabService;
    }
    private CabService cabService;

    /**
     * Method used to post Cab details
     * @param {@link @RequestBody CabDto}cabDto object with required details
     * @return {@link String}returns the status of the given details
     */
    @PostMapping("create")
    public String addCabDetails(@RequestBody CabDto cabDto) {
        String message = " Failed :: Not Inserted ";
        if (null != cabDto) {
            return cabService.createCab(cabDto);
        } else{
            return message;
        }
    }

    /**
     * Method used to put Cab details
     * @param {@link @RequestBody CabDto}cabDto object with required details
     * @return {@link String}returns the status of the given details
     */
    @PutMapping("update")
    public String updateCabDetails(@RequestBody CabDto cabDto) {
        String message = " Failed :: Not Inserted ";
        if (null != cabDto) {
            int id = cabDto.getId();
            return cabService.updateCabDetailsById(id,cabDto);
        } else{
            return message;
        }
    }

    /**
     * Method used to get trainee details  from server
     * @param {no param}
     * @return {@link List<CabDto>}returns all the trainee details
     */
    @GetMapping("cabs")
    public List<CabDto> getAllCabsDetails()  {

        return cabService.showAllCabDetails();

    }

    /**
     * Method used to get  details  from server by using id
     * @param {@link int} id
     * @return {@link List<Cab>}returns cab details
     */
    @GetMapping("search/{id}")
    public CabDto getCabDetailsById(@PathVariable int id)  {

        return  cabService.displayCabDetailsById(id);

    }

    /**
     * Method used to get  details  from server by using id
     * @param {@link int} id
     * @return {@link List<Cab>}returns cab details
     */
    @DeleteMapping("delete/{id}")
    public String deleteCabDetailsById(@PathVariable int id)  {

        return cabService.deleteCabDetailsById(id);
    }

}
