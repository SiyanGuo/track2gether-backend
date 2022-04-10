package com.revature.Track2gether.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.CategoryRepository;
import com.revature.Track2gether.service.TransactionService;
import com.revature.Track2gether.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Profile("prod")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class ProductionController {

    @Autowired
    private UserService userservice;

    @Autowired
    private TransactionService transactionservice;

    @Autowired
    private CategoryRepository catrepo;
    Transactiondto dto = new Transactiondto();
    DateFormat df = new SimpleDateFormat("mm/dd/yyyy");

    @PostMapping("/users/{userid}/transaction")
    public ResponseEntity<?> addTransaction(@PathVariable("userid") String userid, @RequestBody Transactiondto dto) throws ParseException {
        Transaction transadd = new Transaction();
        Users user = userservice.getUserById(Integer.parseInt(userid));
        Category category = catrepo.getById(dto.getCategoryid());
        Date dt = df.parse(dto.getDate());
        transadd.setAmount(dto.getAmount());
        transadd.setDate(dt);
        transadd.setDescription(dto.getDescription());
        transadd.setUser(user);
        transadd.setCategory(category);
        Transaction newtrans = transactionservice.addTransaction(transadd);
        //setting
        dto.setId(newtrans.getId());
        dto.setAmount(newtrans.getAmount());
        dto.setDate((df.format(newtrans.getDate())).toString());
        dto.setDescription(newtrans.getDescription());
        dto.setCategoryid(newtrans.getCategory().getId());
        dto.setCategoryname(newtrans.getCategory().getCategoryname());
        dto.setCategoryType(newtrans.getCategory().getTranstype().getType());
        dto.setUserid(newtrans.getUser().getId());
        dto.setFirstname(newtrans.getUser().getFirstname());
        dto.setLastname(newtrans.getUser().getLastname());
        dto.setEmail(newtrans.getUser().getEmail());
        return ResponseEntity.ok(dto);

    }

    /*___________________________________*/
    @GetMapping("/users/{userid}/transactions")
    public ResponseEntity<?> getTransactionByUserId(@PathVariable("userid") String userid) {
        Transaction transadd = new Transaction();
        Users user = userservice.getUserById(Integer.parseInt(userid));
        List<Transaction> getTrans = transactionservice.findByUser(user);
        List<Transactiondto> responses = new ArrayList<Transactiondto>();
        for (Transaction t : getTrans) {
            Transactiondto dto = new Transactiondto();
            dto.setId(t.getId());
            dto.setAmount(t.getAmount());
            dto.setDate((df.format(t.getDate())).toString());
            dto.setDescription(t.getDescription());
            dto.setCategoryid(t.getCategory().getId());
            dto.setCategoryname(t.getCategory().getCategoryname());
            dto.setCategoryType(t.getCategory().getTranstype().getType());
            dto.setUserid(t.getUser().getId());
            dto.setFirstname(t.getUser().getFirstname());
            dto.setLastname(t.getUser().getLastname());
            dto.setEmail(t.getUser().getEmail());
            responses.add(dto);
        }//System.out.println(getTrans);
        return ResponseEntity.ok(responses);
    }

  /*  @GetMapping("/users/{userid}/transactions/expense")
    public ResponseEntity<?> getTransactionByTranstype(@PathVariable("userid") String userid) {
        Transaction transadd = new Transaction();
        Users user = userservice.getUserById(Integer.parseInt(userid));
        int transtype=1;
        List<Transaction> getTrans = transactionservice.findByTransactiontype(transtype);
        List<Transactiondto> responses = new ArrayList<Transactiondto>();
        for (Transaction t : getTrans) {
            Transactiondto dto = new Transactiondto();
            dto.setId(t.getId());
            dto.setAmount(t.getAmount());
            dto.setDate((df.format(t.getDate())).toString());
            dto.setDescription(t.getDescription());
            dto.setCategoryid(t.getCategory().getId());
            dto.setCategoryname(t.getCategory().getCategoryname());
            dto.setCategoryType(t.getCategory().getTranstype().getType());
            dto.setUserid(t.getUser().getId());
            dto.setFirstname(t.getUser().getFirstname());
            dto.setLastname(t.getUser().getLastname());
            dto.setEmail(t.getUser().getEmail());
            responses.add(dto);
        }//System.out.println(getTrans);
        return ResponseEntity.ok(responses);
    }*/

    /*___________________________________*/
    @PutMapping("/users/{userid}/transaction/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("userid") String userid,@PathVariable("id") String id,@RequestBody Transactiondto dto) throws ParseException {
        Transaction transadd = new Transaction();
        Users user = userservice.getUserById(Integer.parseInt(userid));
        Category category = catrepo.getById(dto.getCategoryid());
        transadd.setId(Integer.parseInt(id));
        Date dt = df.parse(dto.getDate());
        transadd.setAmount(dto.getAmount());
        transadd.setDate(dt);
        transadd.setDescription(dto.getDescription());
        transadd.setUser(user);
        transadd.setCategory(category);
        Transaction newtrans = transactionservice.updateTransaction(transadd);
        dto.setId(newtrans.getId());
        dto.setAmount(newtrans.getAmount());
        dto.setDate((df.format(newtrans.getDate())).toString());
        dto.setDescription(newtrans.getDescription());
        dto.setCategoryid(newtrans.getCategory().getId());
        dto.setCategoryname(newtrans.getCategory().getCategoryname());
        dto.setCategoryType(newtrans.getCategory().getTranstype().getType());
        dto.setUserid(newtrans.getUser().getId());
        dto.setFirstname(newtrans.getUser().getFirstname());
        dto.setLastname(newtrans.getUser().getLastname());
        dto.setEmail(newtrans.getUser().getEmail());
        return ResponseEntity.ok(dto);

    }
    /*___________________________________*/
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") String id)
    {
        int transid=Integer.parseInt(id);
        transactionservice.deleteTransactionById(transid);
        return ResponseEntity.ok().build();
    }


}