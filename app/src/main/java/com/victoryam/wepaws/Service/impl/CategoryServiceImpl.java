package com.victoryam.wepaws.Service.impl;

import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Service.ICategoryService;

import java.util.LinkedList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    public List<Category> getAllCategory() {
        return new LinkedList<Category>();
    }

    public void addCategory(Category category) {

    }

    public void updateCategory(Category category) {

    }

    public Category getCategoryById(int categoryId) {
        return new Category();
    }

    public void removeCategoryById(int categoryId) {

    }
}
