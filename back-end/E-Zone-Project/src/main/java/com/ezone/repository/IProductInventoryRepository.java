package com.ezone.repository;

import com.ezone.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductInventoryRepository extends JpaRepository<ProductInventory, Integer>, JpaSpecificationExecutor<ProductInventory>{
}
