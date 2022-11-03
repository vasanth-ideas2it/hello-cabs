/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hellocabs.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByCustomerMobileNumber(Long username);
    boolean existsByCustomerMobileNumber(Long mobileNumber);
    boolean existsByCustomerEmail(String email);
    Customer findByCustomerMobileNumberAndPassword(Long number ,String password);
    Customer findByCustomerIdAndIsDeleted(Integer customerId, boolean False);
    List<Customer> findAllByIsDeleted(boolean False);
}
