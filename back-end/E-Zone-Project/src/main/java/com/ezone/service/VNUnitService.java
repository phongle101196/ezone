package com.ezone.service;

import com.ezone.entity.vn_unit.*;
import com.ezone.repository.vn_unit_repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VNUnitService implements IVNUnitService {
    @Autowired
    private IAdminUnitRepository adminUnitRepository;

    @Autowired
    private IAdminRegionRepository adminRegionRepository;
    @Autowired
    private IProvinceRepository provinceRepository;
    @Autowired
    private IDistrictRepository districtRepository;
    @Autowired
    private IWardRepository wardRepository;

    @Override
    public AdminRegion getAdminRegionById(int id) {
        return adminRegionRepository.findById(id).get();
    }

    @Override
    public AdminUnit getAdminUnitById(int id) {
        return adminUnitRepository.findById(id).get();
    }

    @Override
    public Province getProvinceByCode(String code) {
        return provinceRepository.findById(code).get();
    }

    @Override
    public District getDistrictByCode(String code) {
        return districtRepository.findById(code).get();
    }

    @Override
    public Ward getWardByCode(String code) {
        return wardRepository.findById(code).get();
    }

    @Override
    public List<Ward> getAllWard() {
        return wardRepository.findAll();
    }

    @Override
    public List<Ward> getAllWardByDistrictCode(String code) {
        return    wardRepository.findByDistrictCode(code);

    }

    @Override
    public List<District> getAllDistrict() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> getAllDistrictByProvinceCode(String code) {

        return districtRepository.findByProvinceCode(code);
    }

    @Override
    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }
}
