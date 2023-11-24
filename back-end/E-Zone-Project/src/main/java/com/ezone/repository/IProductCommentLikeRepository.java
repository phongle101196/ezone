package com.ezone.repository;

import com.ezone.entity.ProductCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IProductCommentLikeRepository extends JpaRepository<ProductCommentLike, Integer>, JpaSpecificationExecutor<ProductCommentLike> {
    List<ProductCommentLike> findByProductCommentId(int commentId);

    ProductCommentLike findByProductCommentIdAndUserId(int commentId, int userId);
}
