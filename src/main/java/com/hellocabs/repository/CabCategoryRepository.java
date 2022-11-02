/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.repository;

import com.hellocabs.model.CabCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabCategoryRepository extends JpaRepository<CabCategory,Integer> {
    CabCategory findByIdAndIsDeleted(Integer id, boolean value);
    List<CabCategory> findAllByIsDeleted(boolean value);

    boolean existsByIdAndIsDeleted(Integer id, boolean value);

    boolean existsByCabType(String cabType);
}
