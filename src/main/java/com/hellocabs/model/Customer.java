/*
 * All copyrights reserved.
 */
package com.hellocabs.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
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
    @Column(name = "name")
    private String customerName;
    @Column(name = "mobile_number")
    private long customerMobileNumber;
    @Column(name = "email")
    private String customerEmail;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "ride_id")
    private Set<Ride> rides;
}
