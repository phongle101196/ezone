package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private LocalDateTime createdDate;
    private String status;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String os;
    private String cpu;
    private String gpu;
    private String ram;
    private String rom;
    private String screenSize;
    private String resolution;
    private String camera;
    private String battery;
    private String sim;
    private String bluetooth;
    private String connectionPort;
    private String headphoneJack;
    private String material;
    private String dimensions;
    private String waterResistance;
    private String faceMaterial;
    private String desc;
    private int manufactoryCategoryId;
    private String manufactoryCategoryName;
    private int manufactoryId;
    private String manufactoryName;
    private List<ProductInventoryDTO> productInventories;
    private List<ProductRatingDTO> productRatings;
}
