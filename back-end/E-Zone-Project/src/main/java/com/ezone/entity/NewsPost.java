package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news_post")
@Data
@NoArgsConstructor
public class NewsPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private NewsTopic topic;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1000)
    private String cover;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "post")
    private List<NewsComment> newsComments;

    @OneToMany(mappedBy = "post")
    private List<NewsPostLike> newsPostLikes;
}
