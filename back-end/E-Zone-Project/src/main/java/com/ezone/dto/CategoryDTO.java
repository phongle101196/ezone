package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;
    private List<ManufactoryDTO> manufactories;
}
