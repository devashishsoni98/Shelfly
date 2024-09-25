//package app.server.inventory.repository;
//
//
//import app.server.inventory.entities.Transaction;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    // Add custom query methods if needed
//}

package app.server.inventory.repository;

import app.server.inventory.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}