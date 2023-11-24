package com.ezone.repository;

import com.ezone.entity.ProductVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IProductVoucherRepository extends JpaRepository<ProductVoucher, Integer>, JpaSpecificationExecutor<ProductVoucher> {
    List<ProductVoucher> findByVoucherId(int voucherId);

    List<ProductVoucher> findByProductId(int productId);
}
