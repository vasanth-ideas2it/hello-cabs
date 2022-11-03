/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hellocabs.constants.HelloCabsConstants;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.List;

/**
 * <p>
 * CabCategoryDto class has the getters and setters that holds the
 * data of the fields related to cab category.
 * </p>
 *
 * @author  Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@Data
public class CabCategoryDto {

    private Integer id;

    @NotBlank(message = HelloCabsConstants.CAB_TYPE_NOT_BLANK)
    @Pattern(regexp = ("([a-zA-Z//s]{3,})"))
    private String cabType;

    @DecimalMin(HelloCabsConstants.DECIMAL_MIN)
    private Double initialFare;

    @DecimalMin(HelloCabsConstants.DECIMAL_MIN)
    private Double extraFarePerHour;

    @DecimalMin(HelloCabsConstants.DECIMAL_MIN)
    private Double peakHourFare;

    @JsonIgnore
    private List<CabDto> cabs;
}
