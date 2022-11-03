package com.hellocabs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
    private Integer id;

    @Column(name = "cab_type")
    private String cabType;

    @Column(name = "initial_fare")
    private Double initialFare;

    @Column(name = "extra_fare_per_hour")
    private Double extraFarePerHour;

    @Column(name = "peak_hour_fare")
    private Double peakHourFare;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cab_category_id")
    List<Cab> cabs;
}
