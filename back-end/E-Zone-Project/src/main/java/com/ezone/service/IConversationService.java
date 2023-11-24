package com.ezone.service;

import com.ezone.entity.Conversation;
import com.ezone.form.create.CreatingConversationForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingConversationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConversationService {
    Page<Conversation> getAllConversation(Pageable pageable);

    void createConversation(CreatingConversationForm form);

    void updateConversation(UpdatingConversationForm form);

    void deleteConversationById(int ConversationId);

    Conversation findById(int id);
}
