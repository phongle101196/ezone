package com.ezone.service;

import com.ezone.entity.Bill;
import com.ezone.form.create.CreatingBillForm;
import com.ezone.form.filter.FilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBillService {
    Page<Bill> getAllBill(Pageable pageable, FilterForm form);

    void createNewBill(CreatingBillForm form);

    void deleteBillById(int billId);

    Bill findById(int id);
}
