package com.ezone.dto;

import com.ezone.entity.Voucher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderVoucherDTO {
    private int id;
    private int orderId;
    private int voucherId;
    private String voucherCode;
    private int voucherDiscountAmount;
}
