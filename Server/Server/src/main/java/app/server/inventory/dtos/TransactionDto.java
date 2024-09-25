//package app.server.inventory.dtos;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Builder
//public class TransactionDto {
//    private Long transactionId;
//    private Long productId; // Reference to the product ID.
//    private int changeQuantity; // Positive for IN, Negative for OUT.
//    private String transactionType; // e.g., IN or OUT.
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
//    private LocalDateTime transactionDate; // Format for date-time.
//}

package app.server.inventory.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionDto {
    private Long transactionId;
    private Long productId; // Reference to the product ID.
    private int changeQuantity; // Positive for IN, Negative for OUT.
    private String transactionType; // e.g., IN or OUT.

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime transactionDate; // Format for date-time.
}