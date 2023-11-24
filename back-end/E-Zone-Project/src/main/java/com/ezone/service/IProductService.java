package com.ezone.service;

import com.ezone.entity.Product;
import com.ezone.form.create.CreatingProductForm;
import com.ezone.form.filter.ProductFilterForm;
import com.ezone.form.update.UpdatingProductForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> getAllProduct(Pageable pageable, ProductFilterForm form);

    Product getProductById (int productId);

    void createNewProduct(CreatingProductForm form);

    void updateProduct(UpdatingProductForm form);

    void deleteProductById(int productId);
}
