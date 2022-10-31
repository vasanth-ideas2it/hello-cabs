package com.hellocabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hellocabs.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByCustomerMobileNumberAndPassword(long number ,String pass);

    Customer findByCustomerMobileNumber(long username);
}
