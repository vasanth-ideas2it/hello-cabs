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

/**
 * <p>
 *   Interface that provides abstract methods
 *   to perform CRUD operations for Customer object
 *   to store data in database
 * </p>
 *
 * @author : Gautam
 * @version 1.0
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByCustomerMobileNumber(Long username);
    boolean existsByCustomerMobileNumberOrCustomerEmail(Long mobileNumber, String email);
    Customer findByCustomerIdAndIsDeleted(Integer customerId, boolean flag);
    Customer findByCustomerMobileNumberAndPassword(Long number ,String password);
    List<Customer> findAllByIsDeleted(boolean flag);


}
