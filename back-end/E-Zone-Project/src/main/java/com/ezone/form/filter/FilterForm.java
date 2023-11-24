package com.ezone.form.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class FilterForm {
    private String search;
    private String customerId;
    private String categoryId;
    private String conversationId;
    private String postId;
    private String userId;
    private String orderUserId;
    private String postCommentId;
    private String topicId;
    private String status;
    private String role;
    private String productId;
    private String productCommentId;
    private String email;
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
}
