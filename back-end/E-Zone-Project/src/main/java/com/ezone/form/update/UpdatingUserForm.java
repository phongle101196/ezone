package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UpdatingUserForm {
    private int id;
    private String email;
    private String fullName;
    private String phoneNumber;
    @Pattern(regexp = "MALE|FEMALE|OTHER")
    private String gender;
    private String avatar;
    private String address;
}
