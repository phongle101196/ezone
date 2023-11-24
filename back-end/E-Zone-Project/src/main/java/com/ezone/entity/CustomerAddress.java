package com.ezone.entity;

import com.ezone.entity.vn_unit.District;
import com.ezone.entity.vn_unit.Province;
import com.ezone.entity.vn_unit.Ward;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer_address", uniqueConstraints = {@UniqueConstraint(name = "UniqueShippingAddressAndCustomer", columnNames = {"customer_id", "shipping_address"})})
@Data
@NoArgsConstructor
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ward_code")
    private Ward ward;

    @Column(name = "shipping_address", length = 100, nullable = false)
    private String shippingAddress;
}
