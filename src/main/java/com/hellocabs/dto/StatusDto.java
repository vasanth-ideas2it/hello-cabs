package com.hellocabs.dto;

import com.hellocabs.constants.HelloCabsConstants;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
public class StatusDto {

    private int categoryId;

    private int rideId;

    private int cabId;

    @NotBlank(message = HelloCabsConstants.RIDE_STATUS_NOT_BLANK)
    private String rideStatus;

}
