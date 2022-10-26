/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h> customerController </h>
 * <p>
 * It implements the customer CRUD manipulations.
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
     *   This method is used to create customer details.
     * </p>
     * @param {@link CustomerDto}customerDto contains customer details.
     * @return {@link ResponseEntity<Integer>}returns created customerId and status.
     */
    @PostMapping("create")
    public ResponseEntity<Integer> createCustomerDetails(@RequestBody CustomerDto customerDto) {
        int customerId = customerService.createCustomerDetails(customerDto).getCustomerId();
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    /**
     * <p>
     *   This Method is used to search customer by its id.
     * </p>
     * @param {@link int}customerId.
     * @return {@link ResponseEntity<Integer>}returns customer details.
     */
    @GetMapping("view/{customerId}")
    public ResponseEntity<CustomerDto> viewCustomerById(@PathVariable int customerId) {
        CustomerDto customerDto = customerService.viewCustomerById(customerId);
        if(null !=customerDto) {
            return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to update customer.
     * </p>
     * @param {@link CustomerDto}customerDto.
     * @return {@link ResponseEntity<String>}returns updated customerId .
     */
    @PutMapping("update")
    public ResponseEntity<String> updateCustomerById(@RequestBody CustomerDto customerDto) {
        int customerId = customerService.updateCustomer(customerDto).getCustomerId();
        if(0 != customerId) {
            return new ResponseEntity<>("CustomerId" + customerId + "is Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("CustomerId" + customerId + "is not Updated", HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to delete customer by its id.
     * </p>
     * @param {@link int}customerId.
     * @return {@link ResponseEntity<String>}returns deleted customerId .
     */
    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable int customerId) {
        boolean deletedCustomer = customerService.deleteCustomerById(customerId);
        if(true == deletedCustomer) {
            return new ResponseEntity<>("CustomerId " + customerId + " is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("CustomerId " + customerId + " is not deleted", HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to display all customers.
     * </p>
     * @return {@link ResponseEntity<String>}returns all customers.
     */
    @GetMapping("customers")
    public ResponseEntity<List<CustomerDto>> retriveAllCustomers() {
        List<CustomerDto> customerDtos = customerService.retrieveCustomers();
        return new ResponseEntity<>(customerDtos, HttpStatus.FOUND);
    }
}
