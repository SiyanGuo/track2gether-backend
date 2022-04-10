package com.revature.Track2gether.repositories;

import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

   // @Query("SELECT t FROM Transaction t INNER JOIN category c on c.id = t.category_id and  c.transtype=?1")
    //public List<Transaction> findByTransactiontype(int transtype);

   public List<Transaction> findByUser(Users user);

}
