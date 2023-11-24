package com.ezone.form.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingNewsPostForm {
    private int userId;
    private int topicId;
    private String title;
    private String cover;
    private String content;
}
