//package app.server.inventory.entities;
//
//import jakarta.persistence.*;
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
//@Entity
//@Table(name = "transactions")
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long transactionId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @Column(name = "change_quantity")
//    private int changeQuantity; // Positive for IN, Negative for OUT
//
//    @Column(name = "transaction_type")
//    private String transactionType; // e.g., IN or OUT
//
//    @Column(name = "transaction_date")
//    private LocalDateTime transactionDate;
//}

package app.server.inventory.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "change_quantity")
    private int changeQuantity; // Positive for IN, Negative for OUT

    @Column(name = "transaction_type")
    private String transactionType; // e.g., IN or OUT
    @CreationTimestamp
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}