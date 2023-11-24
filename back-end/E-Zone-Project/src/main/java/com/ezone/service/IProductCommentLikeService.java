package com.ezone.service;

import com.ezone.entity.ProductCommentLike;
import com.ezone.form.create.CreatingProductCommentLikeForm;

import java.util.List;

public interface IProductCommentLikeService {
    List<ProductCommentLike> getAllProductCommentLikeByCommentId(int commentId);

    ProductCommentLike getProductCommentLikeById(int commentLikeId);

    ProductCommentLike getProductCommentLikeByCommentIdAndUserId(int commentId, int userId);

    void createMewProductCommentLike(CreatingProductCommentLikeForm form);

    void deleteProductCommentLikeById(int commentLikeId);
}
