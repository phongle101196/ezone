package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private int id;
    private ProductInventoryDTO productInventory;
    private int quantity;
}
