//package app.server.inventory.services.impl;
//
//import app.server.inventory.dtos.TransactionDto;
//import app.server.inventory.entities.Product;
//import app.server.inventory.entities.Transaction;
//import app.server.inventory.repository.TransactionRepository;
//import app.server.inventory.services.ProductService;
//import app.server.inventory.services.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TransactionServiceImpl implements TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private ProductService productService;
//
//    @Override
//    public Transaction createTransaction(TransactionDto transactionDto) {
//        Transaction transaction = new Transaction();
//
//        // Fetching the product by ID from ProductService
//        Product product = productService.getProductById(transactionDto.getProductId());
//        if (product != null) {
//            transaction.setProduct(product);
//        } else {
//            throw new IllegalArgumentException("Product not found with ID: " + transactionDto.getProductId());
//        }
//
//        transaction.setChangeQuantity(transactionDto.getChangeQuantity());
//        transaction.setTransactionType(transactionDto.getTransactionType());
//        transaction.setTransactionDate(transactionDto.getTransactionDate());
//
//        return transactionRepository.save(transaction);
//    }
//
//    @Override
//    public Transaction getTransactionById(Long transactionId) {
//        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
//        return optionalTransaction.orElse(null);
//    }
//
//    @Override
//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }
//
//    @Override
//    public Transaction updateTransaction(Long transactionId, TransactionDto transactionDto) {
//        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
//        if (optionalTransaction.isPresent()) {
//            Transaction existingTransaction = optionalTransaction.get();
//
//            // Fetching the product by ID from ProductService for updating if necessary
//            Product product = productService.getProductById(transactionDto.getProductId());
//            if (product != null) {
//                existingTransaction.setProduct(product);
//            }
//
//            existingTransaction.setChangeQuantity(transactionDto.getChangeQuantity());
//            existingTransaction.setTransactionType(transactionDto.getTransactionType());
//            existingTransaction.setTransactionDate(transactionDto.getTransactionDate());
//
//            return transactionRepository.save(existingTransaction);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteTransaction(Long transactionId) {
//        transactionRepository.deleteById(transactionId);
//    }
//}
package app.server.inventory.services.impl;

import app.server.inventory.dtos.TransactionDto;
import app.server.inventory.entities.Product;
import app.server.inventory.entities.Transaction;
import app.server.inventory.mapper.TransactionMapper;
import app.server.inventory.repository.TransactionRepository;
import app.server.inventory.services.ProductService;
import app.server.inventory.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductService productService;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);

        // Fetching the product by ID from ProductService
        Product product = productService.getProductById(transactionDto.getProductId());
        if (product != null) {
            transaction.setProduct(product);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + transactionDto.getProductId());
        }

        return TransactionMapper.mapToTransactionDdto(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
        return optionalTransaction.map(TransactionMapper::mapToTransactionDdto).orElse(null);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::mapToTransactionDdto)
                .toList();
    }

    @Override
    public TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isPresent()) {
            Transaction existingTransaction = optionalTransaction.get();

            // Update fields based on DTO values
            existingTransaction.setChangeQuantity(transactionDto.getChangeQuantity());
            existingTransaction.setTransactionType(transactionDto.getTransactionType());
            existingTransaction.setTransactionDate(transactionDto.getTransactionDate());

            // Fetching the product by ID from ProductService for updating if necessary
            Product product = productService.getProductById(transactionDto.getProductId());
            if (product != null) {
                existingTransaction.setProduct(product);
            }

            return TransactionMapper.mapToTransactionDdto(transactionRepository.save(existingTransaction));
        }
        return null; // Return null if the transaction was not found for update
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
        }
    }
}