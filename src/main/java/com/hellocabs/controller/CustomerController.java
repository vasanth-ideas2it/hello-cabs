/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.model.Customer;
import com.hellocabs.service.CustomerService;
import org.apache.log4j.Logger;
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
 *   This class is used to get and access customer details and
 *   implements customer CRUD operations.
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
    private Logger logger = LoggerConfiguration.getInstance("CustomerController.class");

    /**
     * <p>
     *   This method is used to create customer details.
     * </p>
     * @param customerDto {@link CustomerDto}contains customer details.
     * @return {@link String}returns created customerId and status.
     */
    @PostMapping("create")
    private String createCustomerDetails(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.createCustomerDetails(customerDto);
        if(null != customer) {
            logger.info(HelloCabsConstants.CUSTOMER_REGISTERED + customer );
            return (HelloCabsConstants.CUSTOMER_REGISTERED + customer.getCustomerId() + " " + HttpStatus.CREATED);
        }
        logger.info(HelloCabsConstants.CUSTOMER_NOT_REGISTERED + customer );
        return (HelloCabsConstants.CUSTOMER_NOT_REGISTERED);
    }

    /**
     * <p>
     *   This Method is used to search customer by its id.
     * </p>
     * @param customerId{@link int}
     * @return {@link CustomerDto}returns searched customer details.
     */
    @GetMapping("view/{customerId}")
    private CustomerDto viewCustomerById(@PathVariable int customerId) throws RuntimeException {
        CustomerDto customerDto = customerService.viewCustomerById(customerId);
        if(null == customerDto) {
            logger.error(HelloCabsConstants.CUSTOMER_NOT_FOUND);
            throw new RuntimeException(HelloCabsConstants.CUSTOMER_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.CUSTOMER_FOUND);
        return customerDto;
    }

    /**
     * <p>
     *    This Method is used to update customer.
     * </p>
     * @param customerDto{@link CustomerDto}
     * @return {@link String}returns updated customerId .
     */
    @PutMapping("update")
    private String updateCustomerById(@RequestBody CustomerDto customerDto) throws RuntimeException {
        int customerId = customerService.updateCustomer(customerDto).getCustomerId();
        if(0 == customerId) {
            logger.error(HelloCabsConstants.CUSTOMER_NOT_UPDATED);
            throw new RuntimeException(HelloCabsConstants.CUSTOMER_NOT_UPDATED);
        }
        logger.info(HelloCabsConstants.CUSTOMER_UPDATED);
        return(HelloCabsConstants.CUSTOMER_UPDATED+ customerId + HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to delete customer by its id.
     * </p>
     * @param customerId{@link int}
     * @return {@link String}returns deleted customerId .
     */
    @DeleteMapping("delete/{customerId}")
    private String deleteCustomerById(@PathVariable int customerId) {
        boolean deletedCustomer = customerService.deleteCustomerById(customerId);
        if(true == deletedCustomer) {
            logger.info(HelloCabsConstants.CUSTOMER_DELETED);
            return(HelloCabsConstants.CUSTOMER_DELETED + customerId + HttpStatus.OK);
        }
        logger.info(HelloCabsConstants.CUSTOMER_NOT_DELETED);
        return(HelloCabsConstants.CUSTOMER_NOT_DELETED + customerId + HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     *    This Method is used to display all customers.
     * </p>
     * @return {@link List}returns all customers.
     */
    @GetMapping("customers")
    public List<CustomerDto> retrieveAllCustomers() {
        return customerService.retrieveCustomers();

    }
}
