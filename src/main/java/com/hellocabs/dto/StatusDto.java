package com.hellocabs.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
public class StatusDto {

    //@NotBlank(message = " RIDE ID Should Not be EMPTY ")
    private int rideId;
    //@NotBlank(message = " CAB ID Should Not be EMPTY ")
    private int cabId;
    private int categoryId;
    @NotBlank(message = "RIDE Status Should Not be Empty ")
    private String rideStatus;

}
