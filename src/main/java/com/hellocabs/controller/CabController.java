package com.hellocabs.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.validation.annotation.Validated;
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
    private final CabService cabService;
    private RideService rideService;

    private Logger logger = LoggerConfiguration.getInstance("CabController.class");
    /**
     * Method used to post Cab details
     * @param {@link RequestBody CabDto}cabDto object with required details
     * @return {@link String}returns the status of the given details
     */
    @PostMapping("create")
    public String addCabDetails(@Valid @RequestBody CabDto cabDto) {
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
    @PutMapping("updateCab")
    public String updateCabDetails(@Valid @RequestBody CabDto cabDto) {
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
     * Method used to delete details from server by using id
     * @param {@link int} id
     * @return {@link String>}returns Status of Cab details
     */
    @DeleteMapping("delete/{id}")
    public String deleteCabDetailsById(@PathVariable int id)  {

        return cabService.deleteCabDetailsById(id);
    }

    /**
     * Method used to Confirm the Ride Status by updating ride and cab Status
     * @param {@link RideDto, CabDto}rideDto and cabDto Object
     * @return {@link String>}returns Status of Ride
     */
    @PostMapping("confirmRide")
    public String confirmRide(RideDto rideDto, CabDto cabDto) {
        rideDto.setRideStatus("Accepted");
        RideDto updatedRide = rideService.updateRide(rideDto);

        if ("accepted".equals(updatedRide.getRideStatus())) {
            cabDto.setCabStatus("Unavailable");
            cabService.updateCabDetailsById(cabDto.getId(), cabDto);
            return "Accepted";
        }
        return "Un Accepted";
    }

    /**
     * Method used to get PickUpTime by using Ride Status
     * @param {@link RideDto}rideDto Object
     * @return {@link String}returns Status of Ride
     */
    public void getPickedUpTime(RideDto rideDto) {
        rideDto.setRideStatus("picked");
        LocalDateTime pickUpTime = LocalDateTime.now();
        rideDto.setRideTime(pickUpTime);
        rideService.updateRide(rideDto);
    }

    /**
     * Method used to Calculate TravelFare by using PickUpTime And DropTime
     * @param {@link RideDto, CabDto, CabCategory}rideDto, cabDto, cabCategory Object
     * @return {@link Double}returns RidePrice by Time Of Travel
     */
    public String calculateTravelFare(RideDto rideDto, CabDto cabDto, CabCategory cabCategory) {
        String rideStatus = rideDto.getRideStatus();
        if (("Dropped").equalsIgnoreCase(rideStatus)) {
            cabDto.setCabStatus("Available");
            cabService.updateCabDetailsById(cabDto.getId(), cabDto);
            LocalDateTime time = rideDto.getRideTime();
            int timeDifference = (rideDto.getRideTime().getHour()) - (LocalDateTime.now().getHour());
            rideDto.setPrice((timeDifference) * (cabCategory.getInitialFare()));
            rideService.updateRide(rideDto);

            return "The PRICE AMOUNT : " + rideDto.getPrice() + " Rupees Only";
        } else {
            return " THE CUSTOMER NOT TO BE DROPPED YET :: Please check the ride status";
        }

    }

    /**
     * <p>
     * Method used to show the  exception which is handle by MethodArgumentNotValidException
     * with the help of exception handler method
     * </p>
     *
     * @param {@link MethodArgumentNotValidException}exception
     * @return {@link String}return the exception with message
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public String exceptionHandler(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return " INVALID ENTRY " + errors;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public String exceptionHandler(RuntimeException exception) {
        return "Exception " + exception.getMessage();
    }

    @PutMapping("update/{id}/{cabStatus}")
    public String updateCabStatus(@PathVariable int id, @PathVariable String cabStatus) {
        return cabService.updateCabStatus(id, cabStatus);
       /*CabDto cabDto = cabService.displayCabDetailsById(id);
        String message = " Failed :: THERE IS NO CAB ID To Update ";
        if (null != cabDto) {
            cabDto.setCabStatus(cabStatus);
            message = cabService.updateCabDetailsById(id,cabDto);
             return message + cabStatus + " \n Status Updated Successfully ";
        }
        return message; */
    }
}
