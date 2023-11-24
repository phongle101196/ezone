package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class CreatingUserForm {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String fullName;
    @Pattern(regexp = "MALE|FEMALE|OTHER")
    private String gender;
    private String avatar;
    private String address;
}
