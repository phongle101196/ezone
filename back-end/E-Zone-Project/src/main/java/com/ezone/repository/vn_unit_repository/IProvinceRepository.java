package com.ezone.repository.vn_unit_repository;

import com.ezone.entity.vn_unit.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProvinceRepository extends JpaRepository<Province, String>, JpaSpecificationExecutor<Province> {
}
