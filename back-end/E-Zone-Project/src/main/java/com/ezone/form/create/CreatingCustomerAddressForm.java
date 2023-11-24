package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingCustomerAddressForm {
    private int customerId;
    private String wardCode;
    private String shippingAddress;
}
