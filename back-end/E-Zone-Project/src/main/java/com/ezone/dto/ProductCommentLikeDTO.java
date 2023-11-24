package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductCommentLikeDTO {
    private int id;
    private int userId;
    private String userFullName;
    private int productCommentId;
    private LocalDateTime likeDate;
}
