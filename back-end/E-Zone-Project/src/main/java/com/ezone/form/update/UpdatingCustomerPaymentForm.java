package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class UpdatingCustomerPaymentForm {
    private int id;
    private int customerId;
    private int bankId;
    private String cardNumber;
    private String cardName;
    @DateTimeFormat(pattern = "yy-MM")
    private String cardExpired;
    private String cvc;
}
