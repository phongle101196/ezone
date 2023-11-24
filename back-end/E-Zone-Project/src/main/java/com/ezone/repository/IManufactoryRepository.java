package com.ezone.repository;

import com.ezone.entity.Manufactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IManufactoryRepository extends JpaRepository<Manufactory, Integer>, JpaSpecificationExecutor<Manufactory> {
    List<Manufactory> findByCategoryId(int categoryId);
}
