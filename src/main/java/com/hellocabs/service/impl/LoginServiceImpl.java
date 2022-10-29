package com.hellocabs.service.impl;

import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;
import com.hellocabs.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class LoginServiceImpl implements UserDetailsService {

    private final CustomerService customerService;
    private final CabService cabService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       // User user = customerService.fi
        return null;
    }
}
