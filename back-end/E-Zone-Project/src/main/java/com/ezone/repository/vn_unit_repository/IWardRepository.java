package com.ezone.repository.vn_unit_repository;

import com.ezone.entity.vn_unit.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IWardRepository extends JpaRepository<Ward, String>, JpaSpecificationExecutor<Ward> {
    List<Ward> findByDistrictCode(String code);
}
