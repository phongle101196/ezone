package com.ezone.service;

import com.ezone.entity.ProductSubComment;
import com.ezone.form.create.CreatingProductSubCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingProductSubCommentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductSubCommentService {
    Page<ProductSubComment> getAllProductSubComment(Pageable pageable, FilterForm form);

    ProductSubComment getProductSubCommentById(int subCommentId);

    void createNewProductSubComment(CreatingProductSubCommentForm form);

    void updateProductSubComment(UpdatingProductSubCommentForm form);

    void deleteProductSubCommentById(int subCommentId);
}
