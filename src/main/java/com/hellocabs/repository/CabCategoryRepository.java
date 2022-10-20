package com.hellocabs.repository;

import com.hellocabs.model.CabCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabCategoryRepository extends JpaRepository<CabCategory,Integer> {
}
