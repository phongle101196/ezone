package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingNewsCommentForm {
    private int userId;
    private int postId;
    private String content;
}
