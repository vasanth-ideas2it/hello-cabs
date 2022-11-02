/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 *   A Dto object which is responsible for collect and display
 *   all possible information from user while booking a ride
 * </p>
 *
 * @author : Pradeep
 * created on 31/10/2022
 * @version 1.0
 *
 */
@Data
@Validated
public class BookDto {

    private Long passengerMobileNumber;

    private Integer pickupLocation;

    private Integer dropLocation;

    private Integer customerId;
}
