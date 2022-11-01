/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.model.Customer;
import com.hellocabs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerMobileNumber(Long.parseLong(mobileNumber));
        if (customer != null) {
            String userName = Long.toString(customer.getCustomerMobileNumber());
            return new User(userName, customer.getPassword(), null);
        } else {
            throw new UsernameNotFoundException("User not found with mobile number: " + mobileNumber);
        }
    }
}
