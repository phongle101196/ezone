package com.ezone.service;

import com.ezone.entity.OrderDetail;
import com.ezone.form.create.CreatingOrderDetailForm;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getAllOrderDetailByOrderId(int orderId);

    void createNewOrderDetail(CreatingOrderDetailForm form);

    OrderDetail findById(int id);
}
