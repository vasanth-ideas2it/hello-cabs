/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.LoginDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;
import com.hellocabs.service.impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h> LoginController </h>
 * <p>
 * It implements the authentication for user.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CustomerService customerService;
    private final CabService cabService;
    private final AuthenticationManager authenticationManager;
    private final CustomerServiceImpl customerServiceImpl;

    private final Logger logger = LoggerConfiguration.getInstance("LoginController.class");

    /**
     * <p>
     *   This method is used to verify user is valid or not.
     * </p>
     * @param {@link LoginDto}loginDto contains mobileNumber and password.
     * @return {@link String}returns login status.
     */


    @PostMapping("customer/login")
    public String verifyCustomer(@RequestBody LoginDto loginDto) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerMobileNumber(loginDto.getMobileNumber());
        customerDto.setPassword(loginDto.getPassword());
        return (customerService.verifyCustomerDetails(customerDto));
    }

    @PostMapping("cab/login")
    public String verifyCab(@RequestBody LoginDto loginDto) {
        CabDto cabDto = new CabDto();
        cabDto.setMobileNumber(loginDto.getMobileNumber());
        cabDto.setPassword(loginDto.getPassword());
        return (cabService.verifyCabDetails(cabDto));
    }

    
       // @Autowired
   //     private AuthenticationManager authenticationManager;

  /*      @PostMapping("/login")
        public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getMobileNumber(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        }*/

    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {

        logger.info("In authenticate user()" + loginDto);

        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            Long.toString(loginDto.getMobileNumber()), loginDto.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new HelloCabsException(new BadCredentialsException(HelloCabsConstants.INVALID_LOGIN_CREDENTIALS));
        }
        UserDetails userDetails = customerServiceImpl.loadUserByUsername(Long.toString(loginDto.getMobileNumber()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(HelloCabsConstants.LOGIN_SUCCESSFUL, HttpStatus.OK);
    }
}
