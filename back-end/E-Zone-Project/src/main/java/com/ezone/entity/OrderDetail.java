package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_detail", uniqueConstraints = {@UniqueConstraint(name = "UniqueProductInventoryAndOrder", columnNames = {"order_id", "product_inventory_id"})})
@Data
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_inventory_id", referencedColumnName = "id")
    private ProductInventory productInventory;

    @Column(nullable = false)
    private int quantity;
}
