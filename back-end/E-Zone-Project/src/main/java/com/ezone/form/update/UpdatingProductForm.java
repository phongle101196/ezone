package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
@Data
@NoArgsConstructor
public class UpdatingProductForm {
    private int id;
    private String name;
    @Pattern(regexp = "ACTIVE|DEACTIVE")
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
    private int manufactoryId;
}
