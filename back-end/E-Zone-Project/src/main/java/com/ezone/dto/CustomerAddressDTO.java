package com.ezone.dto;

import com.ezone.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressDTO {
    private int id;
    private String shippingAddress;
    private String wardFullName;
    private String wardDistrictFullName;
    private String wardDistrictProvinceFullName;
}
