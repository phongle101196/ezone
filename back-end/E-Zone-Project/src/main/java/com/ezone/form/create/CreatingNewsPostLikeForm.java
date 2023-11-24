package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingNewsPostLikeForm {
    private int userId;
    private int postId;
}
