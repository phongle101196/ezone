package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingNewsCommentLikeForm {
    private int postCommentId;
    private int userId;
}
