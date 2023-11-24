package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CreatingProductCommentForm {
    private int productId;
    private int userId;
    private String content;
}
