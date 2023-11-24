package com.ezone.service;

import com.ezone.entity.OrderVoucher;
import com.ezone.form.create.CreatingOrderVoucherForm;

import java.util.List;

public interface IOrderVoucherService {
    OrderVoucher getOrderVoucherByOrderId(int orderId);

    void createNewOrderVoucher(CreatingOrderVoucherForm form);

    boolean checkVoucher(String code, List<Integer> productInventoryIds);
}
