package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingBillForm {
    private int orderId;
    private int userId;
    private int discount;
}
