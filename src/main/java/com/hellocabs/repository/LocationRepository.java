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
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    Location findByIdAndIsDeleted(Integer id, boolean value);

<<<<<<< HEAD
    List<Location> findAllByIsDeleted(boolean value);

    boolean existsByLocationName(String locationName);

=======
    boolean existsByIdAndIsDeleted(int id, boolean value);

    boolean existsByLocationName(String locationName);
>>>>>>> changes in customer
    boolean existsByIdAndIsDeleted(Integer id, boolean value);
}
