package com.hellocabs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * LocationDto class has the getters and setters for
 * Location
 *</p>
 *
 * @author Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@Data
public class LocationDto {
    private int id;

    @NotBlank(message = "Location name must not be Empty and null")
    @Size(min=3, message="Location name should have atleast 3 characters")
    private String locationName;
}
