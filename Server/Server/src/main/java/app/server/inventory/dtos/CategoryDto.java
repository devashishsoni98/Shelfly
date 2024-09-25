package app.server.inventory.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
}
