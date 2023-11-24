package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManufactoryDTO {
    private int id;
    private String name;
    private int categoryId;
    private String categoryName;
}
