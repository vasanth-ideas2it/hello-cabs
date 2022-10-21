/*
 * All copyrights reserved.
 */
package com.hellocabs.model;

import lombok.Data;

import javax.persistence.*;

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
    private int CustomerId;
    @Column(name = "name")
    private String CustomerName;
    @Column(name = "mobile_number")
    private long CustomerMobileNumber;
    @Column(name = "email")
    private String CustomerEmail;
}
