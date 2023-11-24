package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class CreatingOrderForm {
    private int userId;
    private String wardCode;
    private String orderAddress;
    @Pattern(regexp = "PREPARING|ONSHIPPING|DONE|FAILED")
    private String status;
}
