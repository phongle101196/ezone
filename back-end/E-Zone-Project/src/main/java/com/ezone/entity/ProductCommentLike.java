package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_comment_like", uniqueConstraints = {@UniqueConstraint(name = "UniqueProductCommentAndUserLike", columnNames = {"product_comment_id", "user_id"})})
@Data
@NoArgsConstructor
public class ProductCommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_comment_id", referencedColumnName = "id")
    private ProductComment productComment;

    @Column(name = "like_date")
    @CreationTimestamp
    private LocalDateTime likeDate;
}
