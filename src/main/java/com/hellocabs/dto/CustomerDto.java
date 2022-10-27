/*
 * All copyrights reserved.
 */
package com.hellocabs.dto;

import com.hellocabs.model.Ride;
import lombok.Data;

import java.util.Set;

/**
 * <p>
 *     CustomerDto have the dataMembers of customer.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@Data
public class CustomerDto {

    private int customerId;
    private String customerName;
    private long customerMobileNumber;
    private String customerEmail;
    private String password;
    private Set<RideDto> rides;
}
