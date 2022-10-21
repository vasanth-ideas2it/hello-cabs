/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Customer;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * It implements the customer manipulations.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * <p>
     *   Get the customer details from the user.
     * </p>
     */
    @PostMapping("create")
    public ResponseEntity<Integer> createCustomerDetails(@RequestBody CustomerDto customerDto) {
        System.out.println("mil        " + customerDto.getCustomerEmail());
        System.out.println("name----------" + customerDto.getCustomerName());
        int customerId = customerService.createCustomerDetails(customerDto).getCustomerId();
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }
    @GetMapping("view/{customerId}")
    public ResponseEntity<CustomerDto> viewCustomerById(@PathVariable int customerId) {
        CustomerDto customerDto = customerService.viewCustomerById(customerId);
        if(null !=customerDto) {
            return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @PutMapping("update")
    public ResponseEntity<String> updateCustomerById(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.updateCustomerById(customerDto);
        if(null != customer) {
            return new ResponseEntity<>("CustomerId" + customerDto.getCustomerId() + "is Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("CustomerId" + customerDto.getCustomerId() + "is not Updated", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable int customerId) {
        boolean deletedCustomer = customerService.deleteCustomerById(customerId);
        CustomerDto customerDto = null;
        if(true == deletedCustomer) {
            return new ResponseEntity<>("CustomerId" + customerDto.getCustomerId() + "is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("CustomerId" + customerDto.getCustomerId() + "is not deleted", HttpStatus.NOT_FOUND);
    }
    @GetMapping("customers")
    public ResponseEntity<List<CustomerDto>> retriveAllCustomers() {
        List<CustomerDto> customerDtos = customerService.retrieveCustomers();
        return new ResponseEntity<>(customerDtos, HttpStatus.FOUND);
    }

}
