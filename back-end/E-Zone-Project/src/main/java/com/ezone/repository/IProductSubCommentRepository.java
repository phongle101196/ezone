package com.ezone.repository;

import com.ezone.entity.ProductSubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductSubCommentRepository extends JpaRepository<ProductSubComment, Integer>, JpaSpecificationExecutor<ProductSubComment> {
}
