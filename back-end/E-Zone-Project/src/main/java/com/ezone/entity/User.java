package com.ezone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 15, unique = true)
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.MALE;

    @Column(length = 1000)
    private String avatar;

    @Column(length = 1000)
    private String address;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column
    private boolean activated;

    @OneToMany(mappedBy = "user")
    List<Channel> channels;

    @OneToMany(mappedBy = "user")
    List<Message> messages;

    @OneToMany(mappedBy = "createUser")
    List<Conversation> conversations;

    @OneToMany(mappedBy = "user")
    List<NewsComment> newsComments;

    @OneToMany(mappedBy = "user")
    List<NewsCommentLike> newsCommentLikes;

    @OneToMany(mappedBy = "user")
    List<NewsPostLike> newsPostLikes;

    @OneToMany(mappedBy = "user")
    List<NewsSubComment> newsSubComments;

    @OneToMany(mappedBy = "user")
    List<NewsPost> newsPosts;

    @OneToMany(mappedBy = "user")
    List<ProductRating> productRatings;

    @OneToMany(mappedBy = "user")
    List<ProductComment> productComments;

    @OneToMany(mappedBy = "user")
    List<ProductSubComment> productSubComments;

    @OneToMany(mappedBy = "user")
    List<ProductCommentLike> productCommentLikes;

    @OneToMany(mappedBy = "user")
    List<Order> orders;
}
