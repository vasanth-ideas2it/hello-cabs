/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.repository;

import com.hellocabs.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *   Interface that provides abstract methods
 *   to perform CRUD operations for location entity
 *   to store data in database
 * </p>
 *
 * @author : Divya
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    Location findByIdAndIsDeleted(Integer id, boolean value);

    List<Location> findAllByIsDeleted(boolean value);

    boolean existsByLocationName(String locationName);

    boolean existsByIdAndIsDeleted(Integer id, boolean value);
}
