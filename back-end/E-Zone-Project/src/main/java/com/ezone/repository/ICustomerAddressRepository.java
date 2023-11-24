package com.ezone.repository;

import com.ezone.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ICustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>, JpaSpecificationExecutor<CustomerAddress> {
    List<CustomerAddress> findByCustomerId(int customerId);
}
