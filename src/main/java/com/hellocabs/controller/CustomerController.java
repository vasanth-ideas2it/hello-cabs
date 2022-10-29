/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.hellocabs.constants.HelloCabsConstants;



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
     * @return {@link String}returns created customerId and status.
     */
    @PostMapping("create")
    public String createCustomerDetails(@RequestBody CustomerDto customerDto) {
        int customerId = customerService.createCustomerDetails(customerDto).getCustomerId();
        if(0 != customerId) {
            return (HelloCabsConstants.CUSTOMER_REGISTERED + customerId + " " + HttpStatus.CREATED);
        }
        return (HelloCabsConstants.CUSTOMER_NOT_REGISTERED);
    }

    /**
     * <p>
     *   This Method is used to search customer by its id.
     * </p>
     * @param {@link int}customerId.
     * @return {@link CustomerDto}returns searched customer details.
     */
    @GetMapping("view/{customerId}")
    public CustomerDto viewCustomerById(@PathVariable int customerId) throws RuntimeException{
        CustomerDto customerDto = customerService.viewCustomerById(customerId);
        if(null == customerDto) {
            throw new RuntimeException(HelloCabsConstants.CUSTOMER_NOT_FOUND);
        }
        return customerDto;
    }

    /**
     * <p>
     *    This Method is used to update customer.
     * </p>
     * @param {@link CustomerDto}customerDto.
     * @return {@link String}returns updated customerId .
     */
    @PutMapping("update")
    public String updateCustomerById(@RequestBody CustomerDto customerDto) throws RuntimeException{
        int customerId = customerService.updateCustomer(customerDto).getCustomerId();
        if(0 == customerId) {
            throw new RuntimeException(HelloCabsConstants.CUSTOMER_NOT_UPDATED);
        }
        return(HelloCabsConstants.CUSTOMER_UPDATED+ customerId + HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to delete customer by its id.
     * </p>
     * @param {@link int}customerId.
     * @return {@link String}returns deleted customerId .
     */
    @DeleteMapping("delete/{customerId}")
    public String deleteCustomerById(@PathVariable int customerId) {
        boolean deletedCustomer = customerService.deleteCustomerById(customerId);
        if(true == deletedCustomer) {
            return(HelloCabsConstants.CUSTOMER_DELETED + customerId + HttpStatus.OK);
        }
        return(HelloCabsConstants.CUSTOMER_NOT_DELETED + customerId + HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to display all customers.
     * </p>
     * @return {@link List}returns all customers.
     */
    @GetMapping("customers")
    public List<CustomerDto> retriveAllCustomers() {
        return customerService.retrieveCustomers();

    }
}
