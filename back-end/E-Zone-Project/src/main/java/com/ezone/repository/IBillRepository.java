package com.ezone.repository;

import com.ezone.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IBillRepository extends JpaRepository<Bill, Integer>, JpaSpecificationExecutor<Bill> {
    List<Bill> findByUserId(int userId);

    Bill findByOrderId(int orderId);
}
