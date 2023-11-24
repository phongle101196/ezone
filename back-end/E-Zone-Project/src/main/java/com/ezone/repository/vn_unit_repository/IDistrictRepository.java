package com.ezone.repository.vn_unit_repository;

import com.ezone.entity.vn_unit.District;
import com.ezone.entity.vn_unit.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IDistrictRepository extends JpaRepository<District, String>, JpaSpecificationExecutor<District> {
    List<District> findByProvinceCode(String code);
}
