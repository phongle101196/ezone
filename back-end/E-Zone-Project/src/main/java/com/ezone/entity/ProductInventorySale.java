package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_inventory_sale")
@Data
@NoArgsConstructor
public class ProductInventorySale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_inventory_id", referencedColumnName = "id")
    private ProductInventory productInventory;

    @Column(name = "sale_price", nullable = false)
    private int salePrice;
}
