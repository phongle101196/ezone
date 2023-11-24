package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInventoryDTO {
    private int id;
    private int cost;
    private int price;
    private int stock;
    private int productId;
    private String productName;
    private String productImage1;
    private int colorId;
    private String colorName;
    private String productStatus;
    private int productManufactoryId;
    private String productManufactoryName;
    private int productManufactoryCategoryId;
    private String productManufactoryCategoryName;
    private ProductInventorySaleDTO productInventorySale;

    @Data
    @NoArgsConstructor
    private static class ProductInventorySaleDTO {
        private int id;
        private int salePrice;
    }
}
