package com.ezone.dto;

import com.ezone.entity.Conversation;
import com.ezone.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDTO {
    private int id;
    private int userId;
    private String userName;
    private String userRole;
    private String userAvatar;
    private int conversationId;
    private String conversationName;
    private String conversationStatus;
    private String content;
    private LocalDateTime createdAt;
}
