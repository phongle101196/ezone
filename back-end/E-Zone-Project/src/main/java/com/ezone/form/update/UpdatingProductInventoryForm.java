package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingProductInventoryForm {
    private int id;
    private int cost;
    private int price;
    private int stock;
}
