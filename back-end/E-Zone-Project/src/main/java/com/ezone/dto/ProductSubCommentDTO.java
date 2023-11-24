package com.ezone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductSubCommentDTO {
    private int id;
    private int userId;
    private int productCommentId;
    private String content;
    private LocalDateTime createdDate;
    private boolean confirmed;
}
