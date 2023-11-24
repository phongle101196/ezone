package com.ezone.repository.vn_unit_repository;

import com.ezone.entity.vn_unit.AdminRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAdminRegionRepository extends JpaRepository<AdminRegion, Integer>, JpaSpecificationExecutor<AdminRegion> {

}
