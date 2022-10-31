/*
 * All copyrights reserved.
 */
package com.hellocabs.mapper;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.model.Customer;
import com.hellocabs.model.Ride;

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
        customer.setPassword(customerDto.getPassword());
        Set<RideDto> rideDtos = customerDto.getRides();

        if (null != rideDtos) {
            customer.setRides(rideDtos.stream()
                    .map(RideMapper::convertRideDtoIntoRide)
                    .collect(Collectors.toSet()));
        }
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
        customerDto.setPassword(customer.getPassword());
        Set<Ride> rides = customer.getRides();

        if (null != rides) {
            customerDto.setRides(rides.stream()
                    .map(RideMapper::convertRideIntoRideDto)
                    .collect(Collectors.toSet()));
        }
        return customerDto;
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
            CustomerDto customerDto = CustomerMapper.covertCustomerToCustomerDto(customer);
            customerDtos.add(customerDto);  
        }
        return customerDtos;
    }
}
