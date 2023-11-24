package com.ezone.repository;

import com.ezone.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductCommentRepository extends JpaRepository<ProductComment, Integer>, JpaSpecificationExecutor<ProductComment> {
}
