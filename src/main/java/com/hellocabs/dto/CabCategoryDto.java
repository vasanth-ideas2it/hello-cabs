package com.hellocabs.dto;

import lombok.Data;

@Data
public class CabCategoryDto {
    private int id;
    private String cabType;
    private double initialFare;
    private double extraKmFare;
    private double additionalFare;
}
