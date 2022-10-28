/*
 * All copyrights reserved.
 */
package com.hellocabs.controller;

import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.CustomerDto;
import com.hellocabs.dto.LoginDto;
import com.hellocabs.service.CabService;
import com.hellocabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginController {
    @Autowired
    CustomerService customerService;
    CabService cabService;
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
        customerDto.setCustomerMobileNumber(loginDto.getCustomerMobileNumber());
        customerDto.setPassword(loginDto.getPassword());
        return (customerService.verifyCustomerDetails(customerDto));
    }

    @PostMapping("cab/login")
    public String verifyCab(@RequestBody LoginDto loginDto) {
        CabDto cabDto = new CabDto();
        cabDto.setMobileNumber(loginDto.getCabMobileNumber());
        cabDto.setPassword(loginDto.getPassword());
        return (cabService.verifyCabDetails(cabDto));
    }
}
