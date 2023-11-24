package com.ezone.service;

import com.ezone.entity.Order;
import com.ezone.form.create.CreatingOrderForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingOrderStatusForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<Order> getAllOrder(Pageable pageable, FilterForm form);

    Order createNewOrder(CreatingOrderForm form);

    void deleteOrderById(int orderId);

    Order findById(int id);

    void updateOrderStatus(UpdatingOrderStatusForm form);
}
