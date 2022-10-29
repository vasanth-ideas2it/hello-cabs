/*
 * All copyrights reserved.
 */
package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.mapper.CustomerMapper;
import com.hellocabs.model.Customer;
import com.hellocabs.repository.CustomerRepository;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <h> customerServiceImpl </h>
 * <p>
 *   This class defines implementation of customerService interface methods.
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
     * <h> customerServiceImpl </h>
     * <p>
     *   This method is used to create customer by customerDto
     *   and convert customerDto to customer and
     *   pass to repository.
     * </p>
     *
     * @param {@link CustomerDto}customerDto contains customer details.
     * @return  {@link Customer}returns createdCustomer.
     */
    @Override
    public Customer createCustomerDetails(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
        Customer createdCustomer =  customerRepository.save(customer);
        return createdCustomer;
    }
    /**
     * <p>
     *   This method is used to search customer by its id and
     *   converts customer to customerDto.
     * </p>
     *
     * @param {@link int}customerId.
     * @return {@link CustomerDto} returns customerDto object.
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
     * This method is used to update existing customers and
     * convert customerDto to customer and add into repository.
     * </p>
     *
     * @param {@link CustomerDto}customerDto contains updatedCustomer details.
     * @return {@link CustomerDto}returns updatedCustomer.
     */
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
        if(customerRepository.existsById(customer.getCustomerId())) {
            return CustomerMapper.covertCustomerToCustomerDto(customerRepository.save(customer));
        }
        return null;
    }

    /**
     * <p>
     *    This Method is used to find customer by its id
     *    and delete if is exist.
     * </p>
     *
     * @param {@link int}customerId.
     * @return {@link ResponseEntity<String>}returns true or false.
     */
    @Override
    public boolean deleteCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(null != customer) {
            customerRepository.delete(customer);
            return true;
        }
         return false;
    }

    /**
     * <p>
     *    This Method is used to display all customers and convert
     *    customer into customerDto.
     * </p>
     * @return {@link List<CustomerDto> }returns all customers.
     */
    @Override
    public List<CustomerDto> retrieveCustomers() {
        return CustomerMapper.convertCustomersToCustomerDtos(customerRepository.findAll());
    }
    /**
     * <p>
     *    This Method is used to display all customers and convert
     *    customer into customerDto.
     * </p>
     * @return {@link List<CustomerDto> }returns all customers.
     */
    @Override
    public String verifyCustomerDetails(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
        long number = customer.getCustomerMobileNumber();
        String pass = customer.getPassword();
        Customer value =  customerRepository.findByCustomerMobileNumberAndPassword(number,pass);
        if(null != value) {
            return HelloCabsConstants.LOGIN_SUCCESSFUL;
        }
        return HelloCabsConstants.LOGIN_NOT_SUCCESSFUL;
    }
}
