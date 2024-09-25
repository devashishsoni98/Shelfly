package app.server.inventory.mapper;

import app.server.inventory.dtos.CategoryDto;
import app.server.inventory.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static Category mapToCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryName(categoryDto.getCategoryName())
                .build();
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
