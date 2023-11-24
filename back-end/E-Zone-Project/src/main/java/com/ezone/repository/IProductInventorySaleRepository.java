package com.ezone.repository;

import com.ezone.entity.ProductInventorySale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductInventorySaleRepository extends JpaRepository<ProductInventorySale, Integer>, JpaSpecificationExecutor<ProductInventorySale> {
 ProductInventorySale findByProductInventoryId(int productInventoryId);
}
