/*
 * All copyrights reserved.
 */
package com.hellocabs.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * <h>Customer entity</h>
 * <p>
 *     Customer class have the dataMembers of customer.
 * </p>
 *
 * @author gautam.
 * @version 1.0.
 */
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int customerId;
    @Column(name = "name",length = 30)
    @NotBlank(message = "Name is mandatory")
    private String customerName;
    @Column(name = "mobile_number")
    @NotNull(message = "mobile number is mandatory")
    private long customerMobileNumber;
    @Column(name = "email",length = 20)
    @NotBlank(message = "email is mandatory")
    private String customerEmail;
    @Column(name = "password",length = 20)
    @NotBlank(message = "password is mandatory")
    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "customer_id")
    private Set<Ride> rides;
}
