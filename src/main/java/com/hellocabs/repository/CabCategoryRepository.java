package com.hellocabs.repository;

import com.hellocabs.model.CabCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabCategoryRepository extends JpaRepository<CabCategory,Integer> {
    CabCategory findByIdAndIsDeleted(int id, boolean value);
    List<CabCategory> findAllByIsDeleted(boolean value);

    boolean existsByIdAndIsDeleted(int id, boolean value);
}
