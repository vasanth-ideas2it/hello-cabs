/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellocabs.constants.HelloCabsConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    private String cabType;

    private Double initialFare;
    private Double extraFarePerHour;
    private Double peakHourFare;
    @JsonIgnore
    private List<CabDto> cabs;
}
