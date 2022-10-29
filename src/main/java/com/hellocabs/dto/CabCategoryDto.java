package com.hellocabs.dto;

import com.hellocabs.model.Cab;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private double initialFare;
    private double extraFarePerHr;
    private double peakHrFare;
    private List<CabDto> cabs;

    public CabCategoryDto() {
    }

    public CabCategoryDto(int id, String cabType, double initialFare, double extraFarePerHr, double peakHrFare, List<CabDto> cabs) {
        this.id = id;
        this.cabType = cabType;
        this.initialFare = initialFare;
        this.extraFarePerHr = extraFarePerHr;
        this.peakHrFare = peakHrFare;
        this.cabs = cabs;
    }
}
