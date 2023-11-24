package com.ezone.form.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingNewsPostForm {
    private int id;
    private String title;
    private String cover;
    private String content;
}
