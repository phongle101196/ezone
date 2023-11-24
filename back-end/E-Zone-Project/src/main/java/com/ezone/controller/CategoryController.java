package com.ezone.controller;

import com.ezone.dto.CategoryDTO;
import com.ezone.entity.Category;
import com.ezone.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{id}")
    public Category findById(@PathVariable(name = "id") int id) {
        return categoryService.findById(id);
    }
}
