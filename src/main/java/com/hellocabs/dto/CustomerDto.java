/*
 * All copyrights reserved.
 */
package com.hellocabs.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    private int customerId;
    @NotBlank(message = "Kindly Enter your name")
    @Size(min = 2, max = 30)
    private String customerName;
    @NotNull(message = "Kindly Enter your mobile number")
    @Digits(integer = 10 , fraction = 0)
    private long customerMobileNumber;
    @Email(message = "Kindly Enter your EmailId")
    private String customerEmail;
    @NotBlank(message = "set password for your security")
    private String password;
    private boolean isDeleted;
    private Set<RideDto> rides;

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
