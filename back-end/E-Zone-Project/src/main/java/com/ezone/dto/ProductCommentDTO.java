package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductCommentDTO {
    private int id;
    private int userId;
    private String userFullName;
    private String userAvatar;
    private String content;
    private LocalDateTime createdDate;
    private boolean confirmed;
    private List<ProductCommentLikeDTO> productCommentLikes;
    private List<ProductSubCommentDTO> productSubComments;
}
