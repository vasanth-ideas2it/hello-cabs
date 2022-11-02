/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hellocabs.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByCustomerMobileNumber(long username);
    boolean existsByCustomerMobileNumber(long mobileNumber);
    boolean existsByCustomerEmail(String email);
    Customer findByCustomerMobileNumberAndPassword(long number ,String password);
}
