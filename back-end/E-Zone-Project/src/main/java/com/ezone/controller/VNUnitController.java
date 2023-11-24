package com.ezone.controller;

import com.ezone.dto.vn_unit_dto.DistrictDTO;
import com.ezone.dto.vn_unit_dto.ProvinceDTO;
import com.ezone.dto.vn_unit_dto.WardDTO;
import com.ezone.entity.vn_unit.District;
import com.ezone.entity.vn_unit.Province;
import com.ezone.entity.vn_unit.Ward;
import com.ezone.service.IVNUnitService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vn_units")
@CrossOrigin("*")
public class VNUnitController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IVNUnitService unitService;

    @GetMapping(value = "/wards")
    public List<WardDTO> getAllWard() {
        List<Ward> wardList = unitService.getAllWard();
        return modelMapper.map(wardList, new TypeToken<List<WardDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/wards/{districtCode}")
    public List<WardDTO> getAllWardByDistrictCode(@PathVariable String districtCode) {
        List<Ward> wardList = unitService.getAllWardByDistrictCode(districtCode);
        return modelMapper.map(wardList, new TypeToken<List<WardDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/districts")
    public List<DistrictDTO> getAllDistrict() {

        List<District> districtList = unitService.getAllDistrict();

        return modelMapper.map(districtList, new TypeToken<List<DistrictDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/districts/{provinceCode}")
    public List<DistrictDTO> getAllDistrictByProvinceCode(@PathVariable String provinceCode) {

        List<District> districtList = unitService.getAllDistrictByProvinceCode(provinceCode);

        return modelMapper.map(districtList, new TypeToken<List<DistrictDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/provinces")
    public List<ProvinceDTO> getAllProvince() {

        List<Province> provinceList = unitService.getAllProvince();

        return modelMapper.map(provinceList, new TypeToken<List<ProvinceDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/ward/{code}")
    public WardDTO getWardByCode(@PathVariable(name = "code") String code) {
        Ward ward = unitService.getWardByCode(code);
        return modelMapper.map(ward, WardDTO.class);
    }

}
