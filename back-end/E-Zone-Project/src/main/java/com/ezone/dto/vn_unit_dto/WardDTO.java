package com.ezone.dto.vn_unit_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WardDTO {
    private String code;
    private String fullName;
    private int adminUnitId;
    private String adminUnitFullName;
    private String districtCode;
    private String districtFullName;
    private String districtProvinceCode;
    private String districtProvinceFullName;
}
