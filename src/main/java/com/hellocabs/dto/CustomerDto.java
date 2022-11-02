/*
 * All copyrights reserved.
 */
package com.hellocabs.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    private Integer customerId;

    @NotBlank(message = "Kindly Enter your name")
    @Pattern(regexp = ("([a-z][A-Z]){4,}"))
    private String customerName;

    @NotNull(message = "Kindly Enter your mobile number")
    @Digits(integer = 10 , fraction = 0)
    @Column(unique = true)
    private Long customerMobileNumber;

    @Email(message = "Kindly Enter your EmailId")
    @Column(unique = true)
    private String customerEmail;

    @NotBlank(message = "set password for your security")
   // @Pattern(regexp = ("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*]){8,16}"))
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
