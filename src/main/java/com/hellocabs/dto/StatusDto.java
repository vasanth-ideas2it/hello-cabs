/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.hellocabs.constants.HelloCabsConstants;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 *   A Dto object that contains id of a ride and
 *   cab and also the ride status
 * </p>
 *
 * @author : Jagan
 * created on 27/10/2022
 * @version 1.0
 *
 */
@Data
@Validated
public class StatusDto {

    private int categoryId;

    private int rideId;

    private int cabId;

    @NotBlank(message = HelloCabsConstants.RIDE_STATUS_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.RIDE_STATUS_REGEX,
            message = HelloCabsConstants.RIDE_STATUS)
    private String rideStatus;

}
