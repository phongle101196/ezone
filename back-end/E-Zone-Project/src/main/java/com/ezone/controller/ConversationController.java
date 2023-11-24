package com.ezone.controller;

import com.ezone.dto.ConversationDTO;
import com.ezone.entity.Conversation;
import com.ezone.form.create.CreatingConversationForm;
import com.ezone.form.update.UpdatingConversationForm;
import com.ezone.service.IConversationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/conversations")
@CrossOrigin("*")
public class ConversationController {
    @Autowired
    private IConversationService conversationService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<ConversationDTO> getAllConversation(Pageable pageable) {
        Page<Conversation> conversationPage = conversationService.getAllConversation(pageable);
        List<Conversation> conversations = conversationPage.getContent();
        List<ConversationDTO> conversationDTOS = modelMapper.map(conversations, new TypeToken<List<ConversationDTO>>() {
        }.getType());
        return new PageImpl<>(conversationDTOS, pageable, conversationPage.getTotalElements());
    }

    @PostMapping
    public void createNewConversation(@RequestBody CreatingConversationForm form) {
        conversationService.createConversation(form);
    }

    @PutMapping(value = "/{conversationId}")
    public void updateConversation(@PathVariable(name = "conversationId") int conversationId, @RequestBody @Valid UpdatingConversationForm form) {
        form.setId(conversationId);
        conversationService.updateConversation(form);
    }

    @DeleteMapping(value = "/{conversationId}")
    public void deleteConversation(@PathVariable(name = "conversationId") int conversationId) {
        conversationService.deleteConversationById(conversationId);
    }

    @GetMapping(value = "/{id}")
    public ConversationDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(conversationService.findById(id), ConversationDTO.class);
    }
}
