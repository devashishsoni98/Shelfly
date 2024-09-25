package app.server.inventory.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long userId;
    private String name;
    private String role;
    private String contact;
}
