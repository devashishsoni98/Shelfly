//package app.server.inventory.services;
//
//import app.server.inventory.dtos.TransactionDto;
//import app.server.inventory.entities.Transaction;
//
//import java.util.List;
//
//public interface TransactionService {
//    Transaction createTransaction(TransactionDto transactionDto);
//    Transaction getTransactionById(Long transactionId);
//    List<Transaction> getAllTransactions();
//    Transaction updateTransaction(Long transactionId, TransactionDto transactionDto);
//    void deleteTransaction(Long transactionId);
//}
package app.server.inventory.services;

import app.server.inventory.dtos.TransactionDto;
import app.server.inventory.entities.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto getTransactionById(Long transactionId);
    List<TransactionDto> getAllTransactions();
    TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto);
    void deleteTransaction(Long transactionId);
}