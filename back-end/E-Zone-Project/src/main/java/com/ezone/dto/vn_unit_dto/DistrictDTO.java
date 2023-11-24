package com.ezone.dto.vn_unit_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DistrictDTO {
    private String code;
    private String fullName;
    private int adminUnitId;
    private String adminUnitFullName;
    private int provinceId;
    private String provinceFullName;
}
