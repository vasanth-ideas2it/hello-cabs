/*
 * All copyrights reserved.
 */
package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellocabs.constants.HelloCabsConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * <p>
 *     CustomerDto have the dataMembers of customer.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@Data
@Validated
public class CustomerDto {

    private Integer customerId;

    @NotBlank(message = HelloCabsConstants.CUSTOMER_NAME_NOT_BLANK)
    @Pattern(regexp = ("([a-zA-Z]{3,})"))
    private String customerName;

    @NotNull(message = HelloCabsConstants.MOBILE_NUMBER_NOT_BLANK)
   // @Pattern(regexp = ("([6-9][0-9]{9})"))
    @Range(min = 10, message = "phone_no should be exact 10 characters." )
    private Long customerMobileNumber;

    @Email(message = HelloCabsConstants.ENTER_VALID_EMAIL)
    private String customerEmail;

    @NotBlank(message = HelloCabsConstants.PASSWORD_NOT_BLANK)
    @Pattern(regexp = ("([a-zA-Z0-9!@#$%^&*]{8,16})"), message = HelloCabsConstants.PASSWORD_CRITERIA)
    private String password;

    @JsonIgnore
    private boolean isDeleted;

    private Set<RideDto> rides;

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
