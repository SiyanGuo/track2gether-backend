package com.revature.Track2gether.service;

import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.TransactionRepository;
import com.revature.Track2gether.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    private TransactionRepository transactionrepo;
    @Autowired
    private UsersRepository userrepo;


    @Override
    public Transaction addTransaction(Transaction t) {
        Transaction  newtransaction = transactionrepo.save(t);
        return newtransaction;
    }

    public List<Transaction> findByUser(Users user) {
        return transactionrepo.findByUser(user);
    }

    /*@Override
    public List<Transaction> findByTransactiontype(int transtype) {
        return transactionrepo.findByTransactiontype( transtype);
    }*/
    @Override
    public Transaction updateTransaction(Transaction t) {
       Transaction targetTrans = transactionrepo.findById(t.getId()).get();
        targetTrans.setAmount(t.getAmount());
        targetTrans.setDate(t.getDate());
        targetTrans.setDescription(t.getDescription());
        targetTrans.setCategory(t.getCategory());
        return targetTrans;
    }

    @Override
    public void deleteTransactionById(int id) {
        Transaction targetTrans = transactionrepo.getById(id);
          transactionrepo.delete(targetTrans);

    }
}
