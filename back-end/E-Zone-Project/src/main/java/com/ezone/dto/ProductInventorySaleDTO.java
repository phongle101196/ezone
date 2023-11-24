package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInventorySaleDTO {
    private int id;
    private int productInventoryProductId;
    private String productInventoryProductName;
    private int salePrice;
}
