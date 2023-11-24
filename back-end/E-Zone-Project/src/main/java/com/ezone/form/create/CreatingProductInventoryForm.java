package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingProductInventoryForm {
    private int cost;
    private int price;
    private int stock;
    private int productId;
    private int colorId;
}
