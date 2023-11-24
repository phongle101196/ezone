package com.ezone.controller;

import com.ezone.dto.MessageDTO;
import com.ezone.entity.Message;
import com.ezone.form.create.CreatingMessageForm;
import com.ezone.service.IMessageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/messages")
@CrossOrigin("*")
public class MessageController {
    @Autowired
    private IMessageService messageService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<MessageDTO> getAllMessageByConversationId(@RequestParam int conversationId) {
        List<Message> messages = messageService.getAllMessageByConversationId(conversationId);
        return modelMapper.map(messages, new TypeToken<List<MessageDTO>>() {
        }.getType());
    }

    @PostMapping
    public void createNewMessage(@RequestBody CreatingMessageForm form) {
        messageService.createNewMessage(form);
    }

    @GetMapping(value = "/{id}")
    public MessageDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(messageService.findById(id), MessageDTO.class);
    }
}
