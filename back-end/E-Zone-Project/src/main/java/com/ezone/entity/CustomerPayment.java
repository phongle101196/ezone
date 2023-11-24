package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Table(name = "customer_payment", uniqueConstraints = {@UniqueConstraint(name = "UniqueBankAndCardNumber", columnNames = {"bank_id", "card_number"})})
@Data
@NoArgsConstructor
public class CustomerPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;

    @Column(name = "card_name", length = 50, nullable = false)
    private String cardName;

    @Column(name = "card_expired", nullable = false)
    private LocalDate cardExpired;

    @Column(length = 3, nullable = false)
    private String cvc;
}
