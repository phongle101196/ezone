package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVoucherDTO {
    private int id;
    private int voucherId;
    private String voucherCode;
    private int productId;
    private String productName;
}
