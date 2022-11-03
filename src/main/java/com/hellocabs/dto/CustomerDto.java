/*
 * All copyrights reserved.
 */
package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Kindly Enter your name")
    @Pattern(regexp = ("([a-zA-Z]{3,})"))
    private String customerName;

    @NotNull(message = "Kindly Enter your mobile number")
    private Long customerMobileNumber;

    @Email(message = "Kindly Enter your EmailId")
    private String customerEmail;

    @NotBlank(message = "set password for your security")
    @Pattern(regexp = ("([a-zA-Z0-9!@#$%^&*]{8,16})"))
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
