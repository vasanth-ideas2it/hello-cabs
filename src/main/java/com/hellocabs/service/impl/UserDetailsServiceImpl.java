/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.model.Cab;
import com.hellocabs.model.Customer;
import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerConfiguration.getInstance("UserDetailsServiceImpl.class");
    private final CustomerService customerService;
    private final CabService cabService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        Customer customer = customerService.findByCustomerMobileNumber(Long.parseLong(mobileNumber));
        Cab cab = cabService.findCabByMobileNumber(Long.parseLong(mobileNumber));

        if (customer != null) {
            String userName = Long.toString(customer.getCustomerMobileNumber());
            return new User(userName, passwordEncoder.encode(customer.getPassword()), new ArrayList<>());
        } else if (cab != null) {
            String userName = Long.toString(cab.getMobileNumber());
            return new User(userName, passwordEncoder.encode(cab.getPassword()), new ArrayList<>());
        }
        throw new UsernameNotFoundException(HelloCabsConstants.INVALID_USERNAME_OR_PASSWORD);
    }
}
