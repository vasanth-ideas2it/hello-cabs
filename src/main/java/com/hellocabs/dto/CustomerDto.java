/*
 * All copyrights reserved.
 */
package com.hellocabs.Dto;

import lombok.Data;

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
    private int CustomerId;
    private String CustomerName;
    private long CustomerMobileNumber;
    private String CustomerEmail;
}
