package com.ezone.dto;

import com.ezone.entity.Channel;
import com.ezone.entity.ConversationStatus;
import com.ezone.entity.Message;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
public class ConversationDTO {
    private int id;
    private String name;
    private int createUserId;
    private String status;
    private List<MessageDTO> messages;
}
