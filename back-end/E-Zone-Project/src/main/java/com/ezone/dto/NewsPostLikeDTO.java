package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewsPostLikeDTO {
    private int id;
    private int userId;
    private String userFullName;
    private LocalDateTime likeDate;
}
