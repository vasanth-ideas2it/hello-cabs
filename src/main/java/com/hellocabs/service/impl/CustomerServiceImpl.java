/*
 * All copyrights reserved.
 */
package com.hellocabs.service.impl;

import com.hellocabs.Dto.CustomerDto;
import com.hellocabs.mapper.CustomerMapper;
import com.hellocabs.model.Customer;
import com.hellocabs.repository.CustomerRepository;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *   CustomerServiceImpl .
 * </p>
 *
 *  @author gautam.
 *  @version 1.0.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
     @Autowired
     CustomerRepository customerRepository;
    /**
     * <p>
     * create customer.
     * </p>
     *
     * @param customerDto.
     * @return customerId.
     */
    @Override
    public Customer createCustomerDetails(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
        Customer createdCustomer =  customerRepository.save(customer);
        return createdCustomer;
    }
    /**
     * <p>
     * display customer.
     * </p>
     *
     * @param customerId.
     * @return customerDto.
     */
    @Override
    public CustomerDto viewCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(null != customer) {
            CustomerDto customerDto = CustomerMapper.covertCustomerToCustomerDto(customer);
            return customerDto;
        }
        return null;
    }

    /**
     * <p>
     * update details of an existing customer.
     * </p>
     *
     * @param customerDto.
     * @return updatedCustomer.
     */
    @Override
    public Customer updateCustomerById(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }
    /**
     * <p>
     *   delete customer by id.
     * </p>
     *
     * @param customerId.
     * @return boolean.
     */
    @Override
    public boolean deleteCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(null != customer) {
            customerRepository.deleteById(customerId);
            return true;
        }
         return false;
    }
}
