package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRatingDTO {
    private int id;
    private int userId;
    private int rating;
    private boolean confirmed;
}
