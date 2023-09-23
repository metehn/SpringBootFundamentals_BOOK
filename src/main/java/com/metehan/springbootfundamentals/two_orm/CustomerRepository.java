package com.metehan.springbootfundamentals.two_orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select s from Customer s")
    List<Customer> findAll();

    @Query("select s from Customer s where s.customerId = :myParam ")
    List<Customer> findCustomerById(@Param("myParam") long myArgument);

}
