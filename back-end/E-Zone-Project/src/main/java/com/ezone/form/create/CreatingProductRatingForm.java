package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingProductRatingForm {
    private int productId;
    private int userId;
    private int rating;
}
