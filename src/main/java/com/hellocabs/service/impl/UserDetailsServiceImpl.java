/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.model.Cab;
import com.hellocabs.model.Customer;
import com.hellocabs.repository.CustomerRepository;
import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerService customerService;
    private final CabService cabService;



    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        Customer customer = customerService.findByCustomerMobileNumber(Long.parseLong(mobileNumber));
        Cab cab = cabService.findCabByMobileNumber(Long.parseLong(mobileNumber));

        if (customer != null) {
            String userName = Long.toString(customer.getCustomerMobileNumber());
            return new User(userName, customer.getPassword(), null);
        } else if (cab != null) {
            String userName = Long.toString(cab.getMobileNumber());
            return new User(userName, cab.getPassword(), null);
        }
        throw new UsernameNotFoundException("User not found with mobile number: " + mobileNumber);

    }
}
