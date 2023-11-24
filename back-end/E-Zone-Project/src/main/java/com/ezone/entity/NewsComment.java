package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news_comment")
@Data
@NoArgsConstructor
public class NewsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private NewsPost post;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "postComment")
    private List<NewsCommentLike> newsCommentLikes;

    @OneToMany(mappedBy = "postComment")
    private List<NewsSubComment> newsSubComments;
}
