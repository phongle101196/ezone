package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingNewsSubCommentForm {
    private int userId;
    private int postCommentId;
    private String content;
}
