package com.ezone.repository;

import com.ezone.entity.OrderDetail;
import com.ezone.form.create.CreatingOrderDetailForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail> {
    List<OrderDetail> findByOrderId(int orderId);
}
