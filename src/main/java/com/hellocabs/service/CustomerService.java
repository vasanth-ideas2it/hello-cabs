/*
 *  Copyright (c) All rights reserved Ideas2IT
 */
package com.hellocabs.service;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.model.Customer;
import java.util.List;

/**
 *  <h> customerService </h>
 * <p>
 *   CustomerService interface has abstract methods of customers.
 * </p>
 *
 *  @author gautam.
 *  @version 1.0.
 */
public interface CustomerService {

    Customer createCustomerDetails(CustomerDto customerDto);

    CustomerDto viewCustomerById(Integer customerId);

    CustomerDto updateCustomer(CustomerDto customerDto);

    boolean deleteCustomerById(Integer customerId);

    List<CustomerDto> retrieveCustomers();

    Customer findByCustomerMobileNumber(long parseLong);
}