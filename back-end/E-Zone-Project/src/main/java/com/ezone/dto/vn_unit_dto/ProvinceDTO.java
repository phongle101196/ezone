package com.ezone.dto.vn_unit_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvinceDTO {
    private String code;
    private String fullName;
    private int adminUnitId;
    private String adminUnitFullName;
    private int adminRegionId;
    private String adminRegionName;
}
