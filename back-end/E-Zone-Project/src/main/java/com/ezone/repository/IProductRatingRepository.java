package com.ezone.repository;

import com.ezone.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IProductRatingRepository extends JpaRepository<ProductRating, Integer>, JpaSpecificationExecutor<ProductRating> {
    List<ProductRating> findByProductId(int productId);
}
