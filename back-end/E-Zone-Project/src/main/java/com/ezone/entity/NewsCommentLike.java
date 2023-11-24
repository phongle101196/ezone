package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_comment_like", uniqueConstraints = {@UniqueConstraint(name = "UniqueNewsCommentAndUserLike", columnNames = {"news_comment_id", "user_id"})})
@Data
@NoArgsConstructor
public class NewsCommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "news_comment_id", referencedColumnName = "id")
    private NewsComment postComment;

    @Column(name = "like_date")
    @CreationTimestamp
    private LocalDateTime likeDate;
}
