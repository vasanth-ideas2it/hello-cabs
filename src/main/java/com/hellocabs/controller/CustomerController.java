/*
 *  Copyright (c) All rights reserved Ideas2IT
 */
package com.hellocabs.controller;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.model.Customer;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.CustomerService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

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
    private final Logger logger = LoggerConfiguration.getInstance(HelloCabsConstants.CUSTOMER_CONTROLLER);

    /**
     * <p>
     *   This is Post call method used to create customer details and passes to customerService.
     * </p>
     *
     * @param customerDto {@link @RequestBody CustomerDto}contains customer details.
     * @return {@link String}returns created customerId and status.
     */
    @PostMapping("create")
    private ResponseEntity<Object> createCustomerDetails(@Valid @RequestBody CustomerDto customerDto) {

        if (null != customerDto) {
            Customer customer = customerService.createCustomerDetails(customerDto);
            logger.info(HelloCabsConstants.CUSTOMER_REGISTERED + customer.getCustomerId());
            return HelloCabsResponseHandler
                    .generateResponse(HelloCabsConstants.CUSTOMER_REGISTERED
                            + customer.getCustomerId() ,HttpStatus.CREATED);
        }
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_ALREADY_EXIST);
    }

    /**
     * <p>
     *   This is Get call method used to search customer from database by using its id
     *   if exist it gets the object related to this id if not gets null.
     * </p>
     *
     * @param customerId {@link @PathVariable Integer}
     * @return {@link ResponseEntity<Object>}returns searched customer details.
     */
    @GetMapping("view/{customerId}")
    private ResponseEntity<Object> viewCustomerById(@PathVariable Integer customerId) {
        CustomerDto customerDto = customerService.viewCustomerById(customerId);
        if (null != customerDto) {
            logger.info(HelloCabsConstants.CUSTOMER_FOUND);
            return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.CUSTOMER_FOUND,HttpStatus.OK, customerDto);
        }
        logger.error(HelloCabsConstants.CUSTOMER_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_FOUND);

    }

    /**
     * <p>
     *    This is Post call method used to update customer and passes to customerService .
     * </p>
     * @param customerDto {@link @RequestBody CustomerDto}
     * @return {@link ResponseEntity<Object>}returns updated customerId .
     */
    @PutMapping("update")
    private ResponseEntity<Object> updateCustomerById(@Valid @RequestBody CustomerDto customerDto) {
        Integer customerId = customerDto.getCustomerId();

        if (null != customerId) {
            customerService.updateCustomer(customerDto);
            logger.info(HelloCabsConstants.CUSTOMER_UPDATED);
            return HelloCabsResponseHandler
                    .generateResponse(HelloCabsConstants.CUSTOMER_UPDATED,HttpStatus.OK);
        }
        logger.error(HelloCabsConstants.CUSTOMER_NOT_UPDATED);
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_UPDATED);
    }

    /**
     * <p>
     *    This is Delete call method used to delete customer from the database by using its id.
     * </p>
     * @param customerId {@link @PathVariable Integer}
     * @return {@link ResponseEntity<Object>}returns deleted customerId .
     */
    @DeleteMapping("delete/{customerId}")
    private ResponseEntity<Object> deleteCustomerById(@PathVariable Integer customerId) {
        boolean deletedCustomer = customerService.deleteCustomerById(customerId);
        if ( deletedCustomer) {
            logger.info(HelloCabsConstants.CUSTOMER_DELETED);
            return HelloCabsResponseHandler
                    .generateResponse(HelloCabsConstants.CUSTOMER_DELETED, HttpStatus.OK);
        }
        logger.info(HelloCabsConstants.CUSTOMER_NOT_DELETED);
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DELETED);
    }

    /**
     * <p>
     *    This Method is used to display all customers.
     * </p>
     * @return {@link List}returns all customers.
     */
    @GetMapping("customers")
    public ResponseEntity<Object> retrieveAllCustomers() {
        return HelloCabsResponseHandler
                .generateResponse(HelloCabsConstants.CUSTOMER_FOUND,
                        HttpStatus.OK,customerService.retrieveCustomers());

    }
}
