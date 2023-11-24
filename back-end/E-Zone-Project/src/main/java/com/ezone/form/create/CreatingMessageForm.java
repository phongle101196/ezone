package com.ezone.form.create;

import com.ezone.entity.Conversation;
import com.ezone.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class CreatingMessageForm {
    private int userId;
    private int conversationId;
    private String content;
}
