package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_inventory", uniqueConstraints = {@UniqueConstraint(name = "UniqueProductAndColorProductInventory", columnNames = {"product_id", "color_id"})})
@Data
@NoArgsConstructor
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column
    private int cost;

    @Column
    private int price;

    @Column
    private int stock;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToOne(mappedBy = "productInventory")
    private ProductInventorySale productInventorySale;
}
