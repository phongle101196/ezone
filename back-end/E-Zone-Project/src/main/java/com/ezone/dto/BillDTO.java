package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BillDTO {
    private int id;
    private int orderId;
    private String orderUserFullName;
    private int userId;
    private String userFullName;
    private int amount;
    private int discount;
    private int total;
    private LocalDateTime createdDate;
}
