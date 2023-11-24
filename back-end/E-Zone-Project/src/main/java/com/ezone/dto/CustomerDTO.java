package com.ezone.dto;

import com.ezone.entity.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private int id;
    private int userId;
    private String userFullName;
    private String userPhoneNumber;
    private String userEmail;
    private String userGender;
    private List<CustomerAddressDTO> customerAddresses;
    private LocalDateTime createdDate;
}
