package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingProductCommentLikeForm {
    private int userId;
    private int productCommentId;
}
