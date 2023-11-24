package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreatingVoucherForm {
    private String code;
    private int discountAmount;
    private int stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean activated;
}
