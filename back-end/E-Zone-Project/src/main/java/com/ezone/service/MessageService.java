package com.ezone.service;

import com.ezone.entity.Conversation;
import com.ezone.entity.ConversationStatus;
import com.ezone.entity.Manufactory;
import com.ezone.entity.Message;
import com.ezone.form.create.CreatingManufactoryForm;
import com.ezone.form.create.CreatingMessageForm;
import com.ezone.repository.IConversationRepository;
import com.ezone.repository.IMessageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IConversationRepository conversationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Message> getAllMessageByConversationId(int conversationId) {
        List<Message> messages = messageRepository.findByConversationId(conversationId);
        messages.sort(Comparator.comparingInt(Message::getId));
        return messages;
    }

    @Override
    public void createNewMessage(CreatingMessageForm form) {
        TypeMap<CreatingMessageForm, Message> typeMap = modelMapper.getTypeMap(CreatingMessageForm.class, Message.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingMessageForm, Message>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        if (form.getConversationId() == 0) {
            form.setConversationId(conversationRepository.findFirstByCreateUserIdOrderByIdDesc(form.getUserId()).getId());
        }
        Conversation conversation = conversationRepository.findById(form.getConversationId()).get();
        if (conversation.getStatus().equals(ConversationStatus.OPENING)) {
            Message message = modelMapper.map(form, Message.class);
            messageRepository.save(message);
        }
    }

    @Override
    public Message findById(int id) {
        return messageRepository.findById(id).get();
    }
}
