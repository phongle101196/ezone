package com.ezone.service;

import com.ezone.entity.Conversation;
import com.ezone.entity.ConversationStatus;
import com.ezone.form.create.CreatingConversationForm;
import com.ezone.form.update.UpdatingConversationForm;
import com.ezone.repository.IConversationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConversationService implements IConversationService {
    @Autowired
    private IConversationRepository conversationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Conversation> getAllConversation(Pageable pageable) {
        return conversationRepository.findAll(pageable);
    }

    @Override
    public void createConversation(CreatingConversationForm form) {
        TypeMap<CreatingConversationForm, Conversation> typeMap = modelMapper.getTypeMap(CreatingConversationForm.class, Conversation.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingConversationForm, Conversation>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Conversation conversation = modelMapper.map(form, Conversation.class);
        conversation.setStatus(ConversationStatus.OPENING);
        conversationRepository.save(conversation);
    }

    @Override
    public void updateConversation(UpdatingConversationForm form) {
        Conversation conversation = conversationRepository.findById(form.getId()).get();
        conversation.setStatus(ConversationStatus.valueOf(form.getStatus()));
        conversation.setName(form.getName());
        conversationRepository.save(conversation);
    }

    @Override
    public void deleteConversationById(int conversationId) {
        conversationRepository.deleteById(conversationId);
    }

    @Override
    public Conversation findById(int conversationId) {
        return conversationRepository.findById(conversationId).get();
    }
}
