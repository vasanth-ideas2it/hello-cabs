/*
 * All copyrights reserved.
 */
package com.hellocabs.service;

import com.hellocabs.Dto.CustomerDto;
import com.hellocabs.model.Customer;

/**
 * <p>
 *   CustomerService interface has abstract methods.
 * </p>
 *
 *  @author gautam.
 *  @version 1.0.
 */
public interface CustomerService {
    Customer createCustomerDetails(CustomerDto customerDto);
    CustomerDto viewCustomerById(int customerId);
    Customer updateCustomerById(CustomerDto customerDto);
    boolean deleteCustomerById(int customerId);
}
