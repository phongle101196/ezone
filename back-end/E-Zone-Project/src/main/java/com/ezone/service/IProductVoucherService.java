package com.ezone.service;

import com.ezone.entity.ProductVoucher;
import com.ezone.form.create.CreatingProductVoucherForm;

import java.util.List;

public interface IProductVoucherService {
    List<ProductVoucher> getProductVoucherByVoucherId(int voucherId);

    List<ProductVoucher> getProductVoucherByProductId(int productId);

    void createNewProductVoucher(CreatingProductVoucherForm form);

    void deleteProductVoucherById(int productVoucherId);
}
