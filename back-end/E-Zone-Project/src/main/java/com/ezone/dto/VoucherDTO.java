package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class VoucherDTO {
    private int id;
    private String code;
    private int discountAmount;
    private int stock;
    private int claimed;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean activated;
    private List<ProductVoucherDTO> productVouchers;
    private List<OrderVoucherDTO> orderVouchers;
}
