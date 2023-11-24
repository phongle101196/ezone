package com.ezone.service;

import com.ezone.entity.Color;
import com.ezone.form.create.CreatingColorForm;
import com.ezone.form.update.UpdatingColorForm;
import com.ezone.repository.IColorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService implements IColorService {
    @Autowired
    private IColorRepository colorRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public void createNewColor(CreatingColorForm form) {
        Color color = modelMapper.map(form, Color.class);
        colorRepository.save(color);
    }

    @Override
    public void updateColor(UpdatingColorForm form) {
        Color color = modelMapper.map(form, Color.class);
        colorRepository.save(color);
    }

    @Override
    public void deleteColorById(int colorId) {
        colorRepository.deleteById(colorId);
    }

    @Override
    public Color findById(int id) {
        return colorRepository.findById(id).get();
    }
}
