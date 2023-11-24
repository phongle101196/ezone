package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UpdatingVoucherForm {
    private int id;
    private int discountAmount;
    private int stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean activated;
}
