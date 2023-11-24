package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_voucher", uniqueConstraints = {@UniqueConstraint(name = "UniqueProductAndVoucher", columnNames = {"product_id", "voucher_id"})})
@Data
@NoArgsConstructor
public class ProductVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    private Voucher voucher;
}
