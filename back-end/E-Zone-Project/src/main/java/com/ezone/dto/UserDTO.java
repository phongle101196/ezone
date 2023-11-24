package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String gender;
    private String avatar;
    private String address;
    private LocalDateTime createdDate;
    private String role;
    private boolean activated;
}
