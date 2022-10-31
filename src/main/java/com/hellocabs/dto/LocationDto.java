package com.hellocabs.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
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

    public LocationDto(int id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }
}
