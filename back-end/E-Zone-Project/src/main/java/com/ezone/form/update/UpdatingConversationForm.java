package com.ezone.form.update;

import com.ezone.entity.ConversationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UpdatingConversationForm {
    private int id;
    private String name;
    @Pattern(regexp = "OPENING|CLOSED")
    private String status;
}
