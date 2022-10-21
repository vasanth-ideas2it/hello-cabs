/*
 * All copyrights reserved.
 */
package com.hellocabs.mapper;

import com.hellocabs.Dto.CustomerDto;
import com.hellocabs.model.Customer;

/**
 * <p>
 *  It has the method to convert customerDto to customer
 *  and customer to customerDto.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
public class CustomerMapper {

    /**
     * <p>
     *     Converting customerDto to customer.
     * </p>
     *
     * @param customerDto
     * @return converted customer
     */
    public static Customer convertCustomerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerMobileNumber(customerDto.getCustomerMobileNumber());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        return customer;
    }

    /**
     * <p>
     *     Converting customer to customerDto.
     * </p>
     *
     * @param customer
     * @return converted customerDto
     */
    public static CustomerDto covertCustomerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setCustomerMobileNumber(customer.getCustomerMobileNumber());
        customerDto.setCustomerEmail(customer.getCustomerEmail());
        return customerDto;
    }
}
