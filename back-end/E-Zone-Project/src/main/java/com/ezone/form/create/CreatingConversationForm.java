package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class CreatingConversationForm {
    private String name;
    private int createUserId;
}
