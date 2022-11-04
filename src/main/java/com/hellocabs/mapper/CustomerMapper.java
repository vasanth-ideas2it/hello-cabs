/*
 * All copyrights reserved.
 */

package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.model.Customer;

import java.util.LinkedList;
import java.util.List;

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
        return MapperConfig.getConfigure().map(customerDto, Customer.class);
    }

    /**
     * <p>
     *     Converting customer to customerDto.
     * </p>
     *
     * @param customer
     * @return converted customerDto
     */
    public static CustomerDto convertCustomerToCustomerDto(Customer customer) {
        return MapperConfig.getConfigure().map(customer, CustomerDto.class);
    }

    /**
     * <p>
     *     Converting customers to customerDtos.
     * </p>
     *
     * @param customers
     * @return converted customerDtos.
     */
    public static List<CustomerDto> convertCustomersToCustomerDtos(List<Customer> customers) {
        List<CustomerDto> customerDtos = new LinkedList<CustomerDto>();
        for (Customer customer : customers) {
            CustomerDto customerDto = CustomerMapper.convertCustomerToCustomerDto(customer);
            customerDtos.add(customerDto);  
        }
        return customerDtos;
    }
}
