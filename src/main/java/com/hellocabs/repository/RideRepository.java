/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.repository;

import com.hellocabs.model.Cab;
import com.hellocabs.model.Location;
import com.hellocabs.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RideRepository extends JpaRepository<Ride, Integer> {

   // Set<Cab> findCabsByLocation(int pickupLocation);
}
