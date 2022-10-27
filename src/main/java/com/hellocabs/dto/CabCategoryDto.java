package com.hellocabs.dto;

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
    private double extraKmFare;
    private double additionalFare;
    private List<CabDto> cabDtos;
}
