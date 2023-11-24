package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewsSubCommentDTO {
    private int id;
    private int userId;
    private String userFullName;
    private String userAvatar;
    private int postCommentId;
    private String content;
    private LocalDateTime createdDate;
}
