package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class LocationDto {
    private int id;

    @NotBlank(message = "Location name must not be Empty or null")
    @Size(min=3, message="Location name should have at least 3 characters")
    private String locationName;

    @JsonIgnore
    private boolean isDeleted;

}
