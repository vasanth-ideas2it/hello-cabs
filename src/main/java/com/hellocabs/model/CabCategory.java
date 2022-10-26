package com.hellocabs.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * <p>
 * CabCategory class has the getters and setters for
 * CabCategory
 *</p>
 *
 * @author Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@Data
@Entity
@Table(name = "cab_category")
public class CabCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "cab_type")
    private String cabType;

    @Column(name = "initial_fare")
    private double initialFare;

    @Column(name = "extra_km_Fare")
    private double extraKmFare;

    @Column(name = "additional_fare")
    private double additionalFare;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
            @JoinColumn(name = "cab_category_id")
    List<Cab> cabs;
}
