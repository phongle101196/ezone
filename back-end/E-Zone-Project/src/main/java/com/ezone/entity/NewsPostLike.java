package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_post_like", uniqueConstraints = {@UniqueConstraint(name = "UniqueNewsPostAndUserLike", columnNames = {"news_post_id", "user_id"})})
@Data
@NoArgsConstructor
public class NewsPostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "news_post_id", referencedColumnName = "id")
    private NewsPost post;

    @Column(name = "like_date")
    @CreationTimestamp
    private LocalDateTime likeDate;
}
