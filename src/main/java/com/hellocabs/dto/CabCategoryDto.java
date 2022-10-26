package com.hellocabs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * CabCategoryDto class has the getters and setters for
 * cab category
 *</p>
 *
 * @author  Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@Data
public class CabCategoryDto {
    private int id;

    @NotBlank(message = "Cab type  must not be Empty or null")
    private String cabType;

    @NotBlank(message = "Initial fare must not be Empty or null")
    private double initialFare;

    @NotBlank(message = "Extra kilometer fare must not be Empty or null")
    private double extraKmFare;

    @NotNull(message = "Additional fare must not be null")
    private double additionalFare;
}
