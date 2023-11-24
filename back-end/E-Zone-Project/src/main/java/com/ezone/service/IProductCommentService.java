package com.ezone.service;

import com.ezone.entity.ProductComment;
import com.ezone.form.create.CreatingProductCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingProductCommentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductCommentService {
    Page<ProductComment> getAllProductComment(Pageable pageable, FilterForm form);

    ProductComment getProductCommentById(int commentId);

    void createNewProductComment(CreatingProductCommentForm form);

    void updateProductComment(UpdatingProductCommentForm form);

    void deleteProductCommentById(int commentId);

    void confirmProductComment(int commentId);
}
