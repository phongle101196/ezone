package com.ezone.dto;

import com.ezone.entity.Customer;
import com.ezone.entity.CustomerAddress;
import com.ezone.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private int userId;
    private String userFullName;
    private String userPhoneNumber;
    private String wardCode;
    private String orderAddress;
    private LocalDateTime createdDate;
    private String status;
    private List<OrderDetailDTO> orderDetails;
    private OrderVoucherDTO orderVoucher;
    private BillDTO bill;
}
