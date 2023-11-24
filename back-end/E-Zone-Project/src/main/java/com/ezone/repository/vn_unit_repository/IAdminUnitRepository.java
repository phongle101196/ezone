package com.ezone.repository.vn_unit_repository;

import com.ezone.entity.vn_unit.AdminUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAdminUnitRepository extends JpaRepository<AdminUnit, Integer>, JpaSpecificationExecutor<AdminUnit> {
}
