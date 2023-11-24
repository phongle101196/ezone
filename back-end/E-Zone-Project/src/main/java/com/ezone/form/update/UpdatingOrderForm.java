package com.ezone.form.update;

import com.ezone.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UpdatingOrderForm {
    private int id;
    private int customerAddressId;
    @Pattern(regexp = "PREPARING|ONSHIPPING|DONE|FAILED")
    private String status;
}
