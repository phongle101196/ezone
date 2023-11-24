package com.ezone.form.check;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CheckingOrderVoucherForm {
    private String code;
    private List<Integer> productInventoryIds;
}
