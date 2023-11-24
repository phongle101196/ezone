package com.ezone.repository;

import com.ezone.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {
    List<Message> findByConversationId(int conversationId);
}
