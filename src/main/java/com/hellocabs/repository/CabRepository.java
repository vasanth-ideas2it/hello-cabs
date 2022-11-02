/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.repository;

import com.hellocabs.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h> CabRepository </h>
 * <p>
 * Interface CabRepository which extends JpaRepository used to
 * access the abstract method in it to cabRepository to connect the database
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@Repository
public interface CabRepository  extends JpaRepository<Cab, Integer> {
    Cab findByMobileNumberAndPassword(Long number, String password);

    Cab findByIdAndIsActive(Integer id, boolean False);
    List<Cab> findAllByIsActive(boolean False);
}
