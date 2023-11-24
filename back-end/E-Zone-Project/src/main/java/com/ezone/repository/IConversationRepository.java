package com.ezone.repository;

import com.ezone.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IConversationRepository extends JpaRepository<Conversation, Integer>, JpaSpecificationExecutor<Conversation> {
Conversation findFirstByOrderByIdDesc();
Conversation findFirstByCreateUserIdOrderByIdDesc(int createUserId);
}
