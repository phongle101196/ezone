package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "create_user_id", referencedColumnName = "id")
    private User createUser;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ConversationStatus status;

    @OneToMany(mappedBy = "conversation")
    private List<Channel> channels;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;
}
