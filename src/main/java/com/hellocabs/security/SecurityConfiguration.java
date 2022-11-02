package com.hellocabs.security;

import com.hellocabs.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <p>
 * SpringSecurityConfiguration class has configuration of 
 * Spring security
 * </p>
 *
 * @author Divya
 * @version 1.0 13 Oct 2022
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * <p>
     * This method returns {@link BCryptPasswordEncoder}
     * </p>
     *
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * <p>
     * This method has the security filter that checks and authorize the user.
     * </p>
     *
     * @param httpSecurity
     * @return {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable().authorizeRequests().antMatchers("/customer/login").permitAll()
                    .antMatchers("/cab/login").permitAll()
                    .antMatchers("/customer/register").permitAll()
                    .antMatchers("/cab/register").permitAll()
                    .antMatchers("/**").permitAll();
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl);
    }



}
