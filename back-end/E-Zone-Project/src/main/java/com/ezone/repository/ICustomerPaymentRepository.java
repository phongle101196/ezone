package com.ezone.repository;

import com.ezone.entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ICustomerPaymentRepository extends JpaRepository<CustomerPayment, Integer>, JpaSpecificationExecutor<CustomerPayment> {
    List<CustomerPayment> findByCustomerId(int customerId);
}
