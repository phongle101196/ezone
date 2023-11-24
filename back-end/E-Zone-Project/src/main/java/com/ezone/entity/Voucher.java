package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(length = 16, nullable = false, unique = true)
    private String code;

    @Column(name = "discount_amount", nullable = false)
    private int discountAmount;

    @Column(nullable = false)
    private int stock;

    @Column
    private int claimed;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column
    private boolean activated;

    @OneToMany(mappedBy = "voucher")
    private List<OrderVoucher> orderVouchers;

    @OneToMany(mappedBy = "voucher")
    private List<ProductVoucher> productVouchers;
}
