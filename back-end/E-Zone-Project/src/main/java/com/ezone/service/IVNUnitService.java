package com.ezone.service;

import com.ezone.entity.vn_unit.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVNUnitService {
    AdminRegion getAdminRegionById(int id);

    AdminUnit getAdminUnitById(int id);

    Province getProvinceByCode(String code);

    District getDistrictByCode(String code);

    Ward getWardByCode(String code);

    List<Ward> getAllWard();

    List<Ward> getAllWardByDistrictCode(String code);

    List<District> getAllDistrict();

    List<District> getAllDistrictByProvinceCode(String code);

    List<Province> getAllProvince();

}
