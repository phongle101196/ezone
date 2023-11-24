package com.ezone.repository;

import com.ezone.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBankRepository extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {
    Bank findByName(String name);
}
