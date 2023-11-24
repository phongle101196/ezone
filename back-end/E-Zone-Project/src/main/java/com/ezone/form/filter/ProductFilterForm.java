package com.ezone.form.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFilterForm {
    private String status;
    private String search;
    private String lowestPrice;
    private String highestPrice;
    private String productId;
    private String manufactoryId;
    private String categoryId;
    private String ram;
    private String rom;

    private String sale;
}
