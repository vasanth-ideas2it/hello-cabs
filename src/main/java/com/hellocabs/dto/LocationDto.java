/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * LocationDto class has the getters and setters that holds the
 * data of the fields related to Location
 * </p>
 *
 * @author Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */

@Data
public class LocationDto {

    private Integer id;

    @NotBlank(message = "Location name must not be Empty or null")
    @Size(min=3, message="Location name should have at least 3 characters")
    private String locationName;

    @JsonIgnore
    private boolean isDeleted;
}
