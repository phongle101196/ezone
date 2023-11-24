package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingProductSubCommentForm {
    private int userId;
    private int productCommentId;
    private String content;
}
