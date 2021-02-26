package com.victoryam.wepaws.Service;

import com.victoryam.wepaws.Domain.Animal;
import com.victoryam.wepaws.Domain.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> getAllCategory();
    public void addCategory(Category category);
    public void updateCategory(Category category);
    public Category getCategoryById(int categoryId);
    public void removeCategoryById(int categoryId);
}
