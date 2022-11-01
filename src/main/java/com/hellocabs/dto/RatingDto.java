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
import javax.validation.constraints.Size;

/**
 * <p>
 *   A Dto object which is responsible for collect feedback
 *   and rating after a ride is completed
 * </p>
 *
 * @author : Pradeep
 * created on 31/10/2022
 * @version 1.0
 *
 */
@Data
@Validated
public class RatingDto {

    private int rideId;

    @NotBlank(message = HelloCabsConstants.RIDE_STATUS_NOT_BLANK)
    @Pattern(regexp = HelloCabsConstants.RIDE_STATUS_REGEX,
            message = HelloCabsConstants.RIDE_STATUS)
    private String rideStatus;

    private double rating;

    @Size(min = 3, message = HelloCabsConstants.FIELD_NOT_BLANK)
    private String feedback;
}
