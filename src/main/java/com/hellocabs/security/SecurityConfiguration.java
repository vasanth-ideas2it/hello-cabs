/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.security;

import com.hellocabs.configuration.LoggerConfiguration;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

/**
 * <p>
 *   Class that contains security configuration
 *   which provides access to users using cab booking application
 *   which configures user credentials and roles
 * </p>
 *
 * @author : Pradeep
 * created on 29/10/2022
 * @version 1.0
 *
 */
//@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final Logger logger = LoggerConfiguration
            .getInstance("SecurityConfiguration.class");

    /*private final RideService rideService;
    //private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BasicAuthenticationFilter basicAuthenticationFilter;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("In config configure(auth)");
       // auth.userDetailsService(userDetailsService)
         //       .passwordEncoder(new BCryptPasswordEncoder());
    }

    protected void securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("In config configure(http)");
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(basicAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return authenticationManagerBean();
    }*/
}
