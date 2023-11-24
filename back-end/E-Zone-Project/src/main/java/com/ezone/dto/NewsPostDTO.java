package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewsPostDTO {
    private int id;
    private int userId;
    private String userFullName;
    private String userAvatar;
    private String title;
    private String cover;
    private String content;
    private LocalDateTime createdDate;
}
