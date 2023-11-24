package com.ezone.service;

import com.ezone.entity.Message;
import com.ezone.form.create.CreatingMessageForm;

import java.util.List;

public interface IMessageService {
    List<Message> getAllMessageByConversationId(int conversationId);

    void createNewMessage (CreatingMessageForm form);

    Message findById(int id);
}
