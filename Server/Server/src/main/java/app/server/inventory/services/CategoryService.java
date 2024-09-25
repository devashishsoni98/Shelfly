package app.server.inventory.services;

import app.server.inventory.dtos.CategoryDto;
import app.server.inventory.entities.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    Category getCategoryById(Long categoryId);
    List<Category> getAllCategories();
    Category updateCategory(Long categoryId, CategoryDto categoryDto);
    void deleteCategory(Long categoryId);
}
