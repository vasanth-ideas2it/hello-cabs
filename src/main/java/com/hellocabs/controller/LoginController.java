/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class LoginController {
  /*  @Autowired
    CustomerService customerService;
    @Autowired
    CabService cabService;
    /**
     * <p>
     *   This method is used to verify user is valid or not.
     * </p>
     * @param {@link LoginDto}loginDto contains mobileNumber and password.
     * @return {@link String}returns login status.
     */
  /*  @PostMapping("customer/login")
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
    } */

    
 //       @Autowired
   //     private AuthenticationManager authenticationManager;

  /*      @PostMapping("/login")
        public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getMobileNumber(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        }*/
}
