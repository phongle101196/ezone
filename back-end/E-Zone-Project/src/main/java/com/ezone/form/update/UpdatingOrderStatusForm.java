package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingOrderStatusForm {
    private int orderId;
    private String status;
}
