package com.ezone.payload;

import com.ezone.dto.UserDTO;
import lombok.Data;

@Data
public class LoginResponse {
    private UserDTO user;
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken, UserDTO user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
