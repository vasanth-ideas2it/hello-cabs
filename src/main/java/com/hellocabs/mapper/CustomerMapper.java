/*
 * All copyrights reserved.
 */
package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Customer;
import com.hellocabs.model.Ride;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private static Logger logger = LoggerConfiguration.getInstance("CustomerMapper.class");

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
