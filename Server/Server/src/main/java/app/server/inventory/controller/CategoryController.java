package app.server.inventory.controller;

import app.server.inventory.dtos.CategoryDto;
import app.server.inventory.entities.Category;
import app.server.inventory.mapper.CategoryMapper;
import app.server.inventory.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto);
        CategoryDto createdCategory = CategoryMapper.mapToCategoryDto(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        CategoryDto foundCategory = CategoryMapper.mapToCategoryDto(category);
        return ResponseEntity.ok(foundCategory);
    }

    // Add more endpoints (e.g., update, delete, getAll) as needed
}
