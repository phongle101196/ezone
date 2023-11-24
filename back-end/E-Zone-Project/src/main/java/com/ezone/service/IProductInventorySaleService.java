package com.ezone.service;

import com.ezone.entity.ProductInventorySale;
import com.ezone.form.create.CreatingProductInventorySaleForm;
import com.ezone.form.update.UpdatingProductInventorySaleForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductInventorySaleService {
    ProductInventorySale getProductInventorySaleById(int inventorySaleId);

    ProductInventorySale getProductInventorySaleByProductInventoryId(int productInventoryId);

    void createNewProductInventorySale(CreatingProductInventorySaleForm form);

    void updateProductInventorySale(UpdatingProductInventorySaleForm form);

    void deleteProductInventorySaleById(int inventorySaleId);

    Page<ProductInventorySale> findAll(Pageable pageable);
}
