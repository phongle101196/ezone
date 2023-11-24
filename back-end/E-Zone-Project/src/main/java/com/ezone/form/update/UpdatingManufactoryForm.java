package com.ezone.form.update;

import com.ezone.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class UpdatingManufactoryForm {
    private int id;
    private int categoryId;
    private String name;
}
