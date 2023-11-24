package com.ezone.service;

import com.ezone.entity.Voucher;
import com.ezone.form.create.CreatingVoucherForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingVoucherForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVoucherService {
    Page<Voucher> getAllVoucher(Pageable pageable);

    Voucher getVoucherByVoucherCode(String code);

    void createNewVoucher(CreatingVoucherForm form);

    void updateVoucher(UpdatingVoucherForm form);

    void deleteVoucherById(int voucherId);
}
