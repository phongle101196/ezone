package com.ezone.repository;

import com.ezone.entity.OrderVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IOrderVoucherRepository extends JpaRepository<OrderVoucher, Integer>, JpaSpecificationExecutor<OrderVoucher> {
    OrderVoucher findByOrderId(int orderId);
}
