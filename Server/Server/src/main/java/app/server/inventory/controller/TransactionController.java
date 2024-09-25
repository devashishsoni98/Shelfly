//package app.server.inventory.controller;
//
//import app.server.inventory.dtos.TransactionDto;
//import app.server.inventory.entities.Transaction;
//import app.server.inventory.mapper.TransactionMapper;
//import app.server.inventory.services.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/transaction")
//public class TransactionController {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @PostMapping
//    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
//        Transaction transaction = transactionService.createTransaction(transactionDto);
//        TransactionDto createdTransaction = TransactionMapper.mapToTransactionDdto(transaction);
//        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") Long transactionId) {
//        Transaction transaction = transactionService.getTransactionById(transactionId);
//        if (transaction == null) {
//            return ResponseEntity.notFound().build();
//        }
//        TransactionDto foundTransaction = TransactionMapper.mapToTransactionDdto(transaction);
//        return ResponseEntity.ok(foundTransaction);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
//        List<Transaction> transactions = transactionService.getAllTransactions();
//        List<TransactionDto> transactionsDto = transactions.stream()
//                .map(TransactionMapper::mapToTransactionDdto)
//                .toList();
//        return ResponseEntity.ok(transactionsDto);
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable("id") Long transactionId,
//                                                            @RequestBody TransactionDto transactionDto) {
//        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transactionDto);
//        if (updatedTransaction == null) {
//            return ResponseEntity.notFound().build();
//        }
//        TransactionDto updatedTransactionDto = TransactionMapper.mapToTransactionDdto(updatedTransaction);
//        return ResponseEntity.ok(updatedTransactionDto);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long transactionId) {
//        transactionService.deleteTransaction(transactionId);
//        return ResponseEntity.noContent().build();
//    }
//
//}
package app.server.inventory.controller;

import app.server.inventory.dtos.TransactionDto;
import app.server.inventory.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Create a new transaction
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto createdTransaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    // Get a specific transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        TransactionDto transaction = transactionService.getTransactionById(id);
        return (transaction != null) ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    // Get all transactions
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Update a specific transaction by ID
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transactionDto);
        return (updatedTransaction != null) ? ResponseEntity.ok(updatedTransaction) : ResponseEntity.notFound().build();
    }

    // Delete a specific transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}