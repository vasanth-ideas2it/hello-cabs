package com.hellocabs.service.impl;

import com.hellocabs.dto.CustomerDto;
import com.hellocabs.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return null;
        // CustomerDto customerDto = customerService.verifyCustomerDetails(Integer.parseInt(username));
    }
}
