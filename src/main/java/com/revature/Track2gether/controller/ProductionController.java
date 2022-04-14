package com.revature.Track2gether.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.Track2gether.dto.*;

import com.fasterxml.jackson.databind.util.JSONPObject;

import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.*;
import com.revature.Track2gether.repositories.CategoryRepository;
import com.revature.Track2gether.service.AuthenticationService;
import com.revature.Track2gether.service.JwtService;
import com.revature.Track2gether.service.TransactionService;
import com.revature.Track2gether.service.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.MalformedJwtException;


import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
@Profile("prod")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class ProductionController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userservice;

    @Autowired
    private TransactionService transactionservice;

    Logger logger= LoggerFactory.getLogger(ProductionController.class);

    @Autowired
    private CategoryRepository catrepo;
    Transactiondto dto = new Transactiondto();
    UserResponseDTO udto = new UserResponseDTO();
    SignUpDTO sdto = new SignUpDTO();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody Users user, Users user2) throws BadParameterException {

        Users newUser = new Users();
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        Users added1 = userservice.addUser(newUser);

        Users spouse = new Users();
        spouse.setFirstname(user2.getFirstname());
        spouse.setLastname(user2.getLastname());
        spouse.setEmail(user2.getEmail());
        spouse.setPassword(user2.getPassword());

        Users added2 = userservice.addUser(spouse);

        int newUserId = added1.getId();
        int spouse_id = newUserId + 1;

        newUser.setSpouseId(new Users(spouse_id, added2.getFirstname(), added2.getLastname(),
                added2.getEmail(), added2.getPassword(), added1));

        System.out.println(newUser.getFirstname());
        System.out.println(newUser.getLastname());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getPassword());

        System.out.println(added1.getId());
        System.out.println(added1.getSpouseId());
        System.out.println(added2.getId());
        System.out.println(added2.getSpouseId());
        System.out.println(newUser.getSpouseId());
        System.out.println(spouse.getSpouseId());

        return ResponseEntity.ok(added1);

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) throws JsonProcessingException {
        try {
            // logger.info("Login functionality...");

            Users users = authService.login(dto.getEmail(), dto.getPassword());

            String jwt = jwtService.createJwt(users);

            HttpHeaders responseHeaders = new HttpHeaders   ();
            responseHeaders.set("token", jwt);

            udto.setId(users.getId());
            udto.setFirstName(users.getFirstname());
            udto.setLastName(users.getLastname());
            udto.setEmail(users.getEmail());
            udto.setSpouseId(users.getSpouseId().getId());
            udto.setSpouseFirstName(users.getSpouseId().getFirstname());
            udto.setSpouseLastName(users.getSpouseId().getLastname());
            udto.setSpouseEmail(users.getSpouseId().getEmail());

            return ResponseEntity.ok().headers(responseHeaders).body(udto);
        } catch (FailedLoginException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (BadParameterException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/users/{userid}/transaction")
    public ResponseEntity<?> addTransaction(@RequestHeader("Authorization") String headerValue,@PathVariable("userid") String userid, @RequestBody Transactiondto dto) throws ParseException, JsonProcessingException, BadParameterException {
        try{
            logger.info("Adding transaction...");
            String jwt = headerValue.split(" ")[1];
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
            try {
                UserJwtDto userdto = jwtService.parseJwt(jwt);
                if(userdto.getUserId()==user.getId()|| userdto.getSpouseId().getId()==user.getId())
                {    dto = transactionservice.addTransaction(transadd);
                    System.out.println(userdto);
                    return ResponseEntity.ok(dto);}
                else{
                    return ResponseEntity.status(401).body("You are not allowed to access this endpoint ");
                }
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(401).body(e.getMessage());

            }}catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage()+"Please validate input");

        }}

    /*___________________________________*/
    @GetMapping("/users/{userid}/transactions")
    public ResponseEntity<?> getTransactionByUserId(@RequestHeader("Authorization")String headerValue, @PathVariable("userid") String userid,
                                                    @RequestParam("transtype") Optional<Integer> transtype) throws BadParameterException {
        try{

            Transaction transadd = new Transaction();
            Users user = userservice.getUserById(Integer.parseInt(userid));
            String jwt = headerValue.split(" ")[1];
            try {
                UserJwtDto userdto = jwtService.parseJwt(jwt);
                if(userdto.getUserId()==user.getId() || userdto.getSpouseId().getId()==user.getId()) {
                    List<Transactiondto> responses = new ArrayList<Transactiondto>();
                    if (transtype.isPresent()) {
                        logger.info("Get all transactions of a user by transactiontype...");
                        responses = transactionservice.findByTransactiontype(user, transtype.get());
                    } else {
                        logger.info("Get all transactions of a user...");
                        responses = transactionservice.findByUser(user);

                    }
                    return ResponseEntity.ok(responses);
                } else{
                    return ResponseEntity.status(401).body("You are not allowed to access this endpoint ");

                }
            }catch (JsonProcessingException e) {
                return ResponseEntity.status(401).body(e.getMessage());

            }}catch (Exception e) {

            return ResponseEntity.status(401).body(e.getMessage()+"Please validate input");}

    }

    @GetMapping("/users/{userid}/transactions/filterby")
    public ResponseEntity<?> getTransactionByTranstype(@RequestHeader("Authorization")String headerValue ,@PathVariable("userid") String userid,
                                                       @RequestParam("year") int year,
                                                       @RequestParam("month") int month) throws BadParameterException {

        try {
            logger.info("Get all transactions of a user by month and year...");
            Transaction transadd = new Transaction();
            Users user = userservice.getUserById(Integer.parseInt(userid));
            String jwt = headerValue.split(" ")[1];
            try {
                UserJwtDto userdto = jwtService.parseJwt(jwt);
                if (userdto.getUserId() == user.getId() || userdto.getSpouseId().getId() == user.getId()) {
                    List<Transactiondto> responses = new ArrayList<Transactiondto>();
                    if (year != 0 && month != 0) {
                        responses = transactionservice.findByTransactions(year, month, user);
                    }

                    return ResponseEntity.ok(responses);}else{
                    return ResponseEntity.status(401).body("You are not allowed to access this endpoint ");
                }
            }catch (JsonProcessingException e) {
                return ResponseEntity.status(401).body(e.getMessage());

            }}catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage()+"Please validate input");}

    }

    /*___________________________________*/
    @PutMapping("/users/{userid}/transaction/{id}")
    public ResponseEntity<?> updateTransaction(@RequestHeader("Authorization")String headerValue,@PathVariable("userid") String userid,@PathVariable("id") String id,@RequestBody Transactiondto dto) throws ParseException, BadParameterException {

        try {
            logger.info("Update transaction of a user...");
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
            String jwt = headerValue.split(" ")[1];
            try {
                UserJwtDto userdto = jwtService.parseJwt(jwt);
                if (userdto.getUserId() == user.getId()) {
                    Transactiondto newtrans = transactionservice.updateTransaction(transadd);
                    return ResponseEntity.ok(newtrans);
                } else {

                    return ResponseEntity.status(401).body("You are not allowed to access this endpoint ");
                }

            } catch (JsonProcessingException e) {
                return ResponseEntity.status(401).body(e.getMessage());

            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage() + "Please validate input");
        }
    }
        /*___________________________________*/
        @GetMapping("/category")
        public ResponseEntity<?> getAllCategory ( @RequestParam("type") int transid)

        {
            logger.info("Get all Category...");
            List<Categorydto> cdto = transactionservice.findByCategoryBytranstype(transid);
            return ResponseEntity.ok(cdto);
        }


        @DeleteMapping("/users/{userid}/transaction/{id}")
        public ResponseEntity<?> deleteTransaction (@RequestHeader("Authorization") String
        headerValue, @PathVariable("userid") String userid, @PathVariable("id") String id) throws
        EntityNotFoundException, BadParameterException {
            try {
                logger.info("Deleting transaction of a user...");
                int transid = Integer.parseInt(id);
                Users user = userservice.getUserById(Integer.parseInt(userid));
                String jwt = headerValue.split(" ")[1];
                try {
                    UserJwtDto userdto = jwtService.parseJwt(jwt);
                    if (userdto.getUserId() == user.getId()) {
                        transactionservice.deleteTransactionById(transid);
                        return ResponseEntity.ok().body("successfully deleted..");
                    } else {
                        return ResponseEntity.status(401).body("You are not allowed to access this endpoint ");
                    }

                } catch (JsonProcessingException e) {
                    return ResponseEntity.status(401).body(e.getMessage());

                }
            } catch (Exception e) {
                return ResponseEntity.status(401).body(e.getMessage() + "Please validate input");
            }
        }

    }