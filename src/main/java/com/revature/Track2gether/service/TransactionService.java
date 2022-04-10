package com.revature.Track2gether.service;

import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;

import java.util.List;

public interface TransactionService {
    public Transaction addTransaction(Transaction t);

    public List<Transaction> findByUser(Users user);

   // public List<Transaction> findByTransactiontype(int transtype);

    public Transaction updateTransaction(Transaction transaction);

    public void deleteTransactionById(int id);
}
