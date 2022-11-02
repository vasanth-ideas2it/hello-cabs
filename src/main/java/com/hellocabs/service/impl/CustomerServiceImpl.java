/*
 * All copyrights reserved.
 */
package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.CustomerMapper;
import com.hellocabs.model.Customer;
import com.hellocabs.repository.CustomerRepository;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
     @Autowired
     CustomerRepository customerRepository;
     @Autowired
    PasswordEncoder passwordEncoder;

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
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        Customer customer = CustomerMapper.convertCustomerDtoToCustomer(customerDto);
        if (!(customerRepository.existsByCustomerMobileNumber(customer.getCustomerMobileNumber()) || customerRepository.existsByCustomerEmail(customer.getCustomerEmail()) )) {
            return  customerRepository.save(customer);
        } else {
            return null;
        }
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
            CustomerDto customerDto = CustomerMapper.convertCustomerToCustomerDto(customer);
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
            return CustomerMapper.convertCustomerToCustomerDto(customerRepository.save(customer));
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
            customer.setIsDeleted(true);
            customerRepository.save(customer);
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
        String password = customer.getPassword();
        //customer.setPassword(passwordEncoder.encode(password));
        Customer login =  customerRepository.findByCustomerMobileNumberAndPassword(number, password);
        System.out.println("returns true or false-----------------" + passwordEncoder.matches(password, login.getPassword()));
        if (passwordEncoder.matches(password, login.getPassword())) {
            return HelloCabsConstants.LOGIN_SUCCESSFUL;
        }
        return HelloCabsConstants.LOGIN_NOT_SUCCESSFUL;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository
                .findByCustomerMobileNumber(Long.parseLong(username));
        if (customer != null) {
            return new User(Long.toString(customer.getCustomerMobileNumber()),
                    customer.getPassword(), new ArrayList<>());
        }
        throw new HelloCabsException(
                new UsernameNotFoundException(HelloCabsConstants.CUSTOMER_NOT_FOUND));
    }
}
