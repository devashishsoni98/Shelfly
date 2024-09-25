//package app.server.inventory.mapper;
//
//import app.server.inventory.dtos.TransactionDto;
//import app.server.inventory.entities.Product;
//import app.server.inventory.entities.Transaction;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TransactionMapper {
//
//    public static Transaction mapToTransaction(TransactionDto transactionDto) {
//        if (transactionDto == null) {
//            return null;
//        }
//
//        return Transaction.builder()
//                .transactionId(transactionDto.getTransactionId())
//                .changeQuantity(transactionDto.getChangeQuantity())
//                .transactionType(transactionDto.getTransactionType())
//                .transactionDate(transactionDto.getTransactionDate())
//                .product(Product.builder().productId(transactionDto.getProductId()).build()) // Set the product using productId
//                .build();
//    }
//
//    public static TransactionDto mapToTransactionDdto(Transaction transaction) {
//        if (transaction == null) {
//            return null;
//        }
//
//        return TransactionDto.builder()
//                .transactionId(transaction.getTransactionId())
//                .changeQuantity(transaction.getChangeQuantity())
//                .transactionType(transaction.getTransactionType())
//                .transactionDate(transaction.getTransactionDate())
//                .productId(transaction.getProduct() != null ? transaction.getProduct().getProductId() : null) // Get product ID if available
//                .build();
//    }
//}

package app.server.inventory.mapper;

import app.server.inventory.dtos.TransactionDto;
import app.server.inventory.entities.Product;
import app.server.inventory.entities.Transaction;

public class TransactionMapper {

    public static Transaction mapToTransaction(TransactionDto dto) {
        if (dto == null) {
            return null;
        }

        return Transaction.builder()
                .transactionId(dto.getTransactionId())
                .changeQuantity(dto.getChangeQuantity())
                .transactionType(dto.getTransactionType())
                .transactionDate(dto.getTransactionDate())
                .product(Product.builder().productId(dto.getProductId()).build()) // Set the product using productId
                .build();
    }

    public static TransactionDto mapToTransactionDdto(Transaction entity) {
        if (entity == null) {
            return null;
        }

        return TransactionDto.builder()
                .transactionId(entity.getTransactionId())
                .changeQuantity(entity.getChangeQuantity())
                .transactionType(entity.getTransactionType())
                .transactionDate(entity.getTransactionDate())
                .productId(entity.getProduct() != null ? entity.getProduct().getProductId() : null) // Get product ID if available
                .build();
    }
}