package com.ezone.controller;

import com.ezone.entity.Color;
import com.ezone.form.create.CreatingColorForm;
import com.ezone.form.update.UpdatingColorForm;
import com.ezone.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/colors")
public class ColorController {
    @Autowired
    private IColorService colorService;

    @GetMapping
    public List<Color> getAllColor() {
        return colorService.getAllColor();
    }

    @PostMapping
    public void createNewColor(@RequestBody CreatingColorForm form) {
        colorService.createNewColor(form);
    }

    @PutMapping(value = "/{colorId}")
    public void updateColor(@PathVariable(name = "colorId") int colorId, @RequestBody UpdatingColorForm form) {
        form.setId(colorId);
        colorService.updateColor(form);
    }

    @DeleteMapping(value = "/{colorId}")
    public void deleteColorById(@PathVariable(name = "colorId") int colorId) {
        colorService.deleteColorById(colorId);
    }

    @GetMapping(value = "/{id}")
    public Color findById(@PathVariable(name = "id") int id){
        return colorService.findById(id);
    }
}
