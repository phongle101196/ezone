package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "image_1", length = 1000)
    private String image1;
    @Column(name = "image_2", length = 1000)
    private String image2;
    @Column(name = "image_3", length = 1000)
    private String image3;
    @Column(name = "image_4", length = 1000)
    private String image4;
    @Column(name = "image_5", length = 1000)
    private String image5;

    @Column(length = 100)
    private String os;

    @Column(length = 100)
    private String cpu;

    @Column(length = 100)
    private String gpu;

    @Column(length = 100)
    private String ram;

    @Column(length = 100)
    private String rom;

    @Column(name = "screen_size", length = 255)
    private String screenSize;

    @Column(length = 255)
    private String resolution;

    @Column(length = 255)
    private String camera;

    @Column(length = 255)
    private String battery;

    @Column(length = 255)
    private String sim;

    @Column(length = 255)
    private String bluetooth;

    @Column(name = "connection_port", length = 255)
    private String connectionPort;

    @Column(name = "headphone_jack", length = 255)
    private String headphoneJack;

    @Column(length = 255)
    private String material;

    @Column(length = 255)
    private String dimensions;

    @Column(name = "water_resistance", length = 255)
    private String waterResistance;

    @Column(name = "face_material", length = 255)
    private String faceMaterial;

    @Column(name = "`desc`")
    private String desc;

    @OneToOne
    @JoinColumn(name = "manufactory_id", referencedColumnName = "id")
    private Manufactory manufactory;

    @OneToMany(mappedBy = "product")
    private List<ProductInventory> productInventories;

    @OneToMany(mappedBy = "product")
    private List<ProductComment> productComments;

    @OneToMany(mappedBy = "product")
    private List<ProductRating> productRatings;

    @OneToMany(mappedBy = "product")
    private List<ProductVoucher> productVouchers;
}
