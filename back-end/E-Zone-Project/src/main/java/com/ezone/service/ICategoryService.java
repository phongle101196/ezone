package com.ezone.service;

import com.ezone.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();

    Category findById(int id);
}
