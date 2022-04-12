package com.revature.Track2gether.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.revature.Track2gether.dto.Categorydto;
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
import java.util.*;

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
        transadd.setShared(dto.isShared());
        dto = transactionservice.addTransaction(transadd);
        return ResponseEntity.ok(dto);
    }

    /*___________________________________*/
    @GetMapping("/users/{userid}/transactions")
    public ResponseEntity<?> getTransactionByUserId(@PathVariable("userid") String userid,
                                                    @RequestParam("transtype") Optional<Integer> transtype) {
        Transaction transadd = new Transaction();
        Users user = userservice.getUserById(Integer.parseInt(userid));
        List<Transactiondto> responses = new ArrayList<Transactiondto>();
        if(transtype.isPresent()){
            responses= transactionservice.findByTransactiontype(user, transtype.get());
        }else{
            responses = transactionservice.findByUser(user);
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/users/{userid}/transactions/filterby")
    public ResponseEntity<?> getTransactionByTranstype(@PathVariable("userid") String userid,
                                                       @RequestParam("year") int year,
                                                       @RequestParam("month") int month){
        Transaction transadd = new Transaction();
        Users user = userservice.getUserById(Integer.parseInt(userid));
        List<Transactiondto> responses = new ArrayList<Transactiondto>();
        if(year!=0 && month!=0){
            responses = transactionservice.findByTransactions( year , month);
        }

        return ResponseEntity.ok(responses);
    }

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
        transadd.setShared(dto.isShared());
        Transactiondto newtrans = transactionservice.updateTransaction(transadd);
        return ResponseEntity.ok(newtrans);

    }
    /*___________________________________*/
    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory(@RequestParam("type") int transid)

    {
        List<Categorydto> cdto = transactionservice.findByCategoryBytranstype(transid);
        return ResponseEntity.ok(cdto);
    }


    @DeleteMapping("/users/{userid}/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("userid") String userid,@PathVariable("id") String id)
    {
        int transid=Integer.parseInt(id);
        Users user = userservice.getUserById(Integer.parseInt(userid));
        transactionservice.deleteTransactionById(transid);
        return ResponseEntity.ok().build();
    }


}