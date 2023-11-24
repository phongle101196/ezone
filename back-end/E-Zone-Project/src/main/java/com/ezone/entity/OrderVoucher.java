package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_voucher", uniqueConstraints = {@UniqueConstraint(name = "UniqueVoucherAndOrder", columnNames = {"order_id", "voucher_id"})})
@Data
@NoArgsConstructor
public class OrderVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}
