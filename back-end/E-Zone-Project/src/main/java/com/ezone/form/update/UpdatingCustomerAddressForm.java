package com.ezone.form.update;

import com.ezone.entity.Customer;
import com.ezone.entity.vn_unit.District;
import com.ezone.entity.vn_unit.Province;
import com.ezone.entity.vn_unit.Ward;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class UpdatingCustomerAddressForm {
    private int id;
    private String wardCode;
    private String shippingAddress;
}
