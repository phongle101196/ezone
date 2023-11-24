package com.ezone.service;

import com.ezone.entity.Color;
import com.ezone.form.create.CreatingColorForm;
import com.ezone.form.update.UpdatingColorForm;

import java.util.List;

public interface IColorService {
    List<Color> getAllColor ();

    void createNewColor (CreatingColorForm form);

    void updateColor (UpdatingColorForm form);

    void deleteColorById(int colorId);

    Color findById(int id);
}
