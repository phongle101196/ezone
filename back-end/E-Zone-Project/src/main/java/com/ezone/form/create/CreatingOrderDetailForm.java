package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CreatingOrderDetailForm {
    private int orderId;
    private int productInventoryId;
    private int quantity;
}
