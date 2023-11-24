package com.ezone.service;

import com.ezone.entity.ProductInventory;
import com.ezone.form.create.CreatingProductInventoryForm;
import com.ezone.form.filter.ProductFilterForm;
import com.ezone.form.update.UpdatingProductForm;
import com.ezone.form.update.UpdatingProductInventoryForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductInventoryService {
    Page<ProductInventory> getAllProductInventory(Pageable pageable, ProductFilterForm form);

    ProductInventory getProductInventoryById(int inventoryId);

    void createNewProductInventory(CreatingProductInventoryForm form);

    void updateProductInventory(UpdatingProductInventoryForm form);

    void deleteProductInventoryById(int inventoryId);
}
