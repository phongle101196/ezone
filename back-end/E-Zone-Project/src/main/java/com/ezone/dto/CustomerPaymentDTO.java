package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CustomerPaymentDTO {
    private int id;
    private int customerId;
    private String customerFullName;
    private int bankId;
    private String bankName;
    private String cardNumber;
    private String cardName;
    private LocalDate cardExpired;
    private String cvc;
}
