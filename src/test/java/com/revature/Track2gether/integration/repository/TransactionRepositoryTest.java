package com.revature.Track2gether.integration.repository;

import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.CategoryRepository;
import com.revature.Track2gether.repositories.TransactionRepository;
import com.revature.Track2gether.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;


import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionRepositoryTest {


    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository catrepo;


    /*
    @Test
    @Transactional
    public void test_findByUser_positive() throws BadParameterException, ParseException {

        Users user = new Users();
        user.setId(1);
        user.setFirstname("Jiwon");
        user.setLastname("Shim");
        user.setEmail("jshim@gmail.com");
        user.setPassword("pass1234");

        List<Transaction> actual = transRepo.findByUser(user);

        List<Transaction> expectedTrans = new ArrayList<>();


        Transaction expected = new Transaction();

        Category category = catrepo.getById(1);

        expected.setId(1);
        expected.setAmount(1840.73);
        expected.setDescription("Bi-weekly wage direct deposit");
        expected.setCategory(category);
        expected.setUser(user);
        expected.setShared(false);

        Transaction expected1 = new Transaction();

        Category category1 = catrepo.getById(6);

        expected1.setId(2);
        expected1.setAmount(213.87);
        expected1.setDescription("Grocery shopping");
        expected1.setCategory(category1);
        expected1.setUser(user);
        expected1.setShared(false);


        expectedTrans.add(expected);
        expectedTrans.add(expected1);

        Assertions.assertEquals(expectedTrans, actual);

    }

     */

    /*
    @Test
    @Transactional
    public void test_findByTransactionType() {

        Users user = new Users();
        user.setId(1);
        user.setFirstname("Jiwon");
        user.setLastname("Shim");
        user.setEmail("jshim@gmail.com");
        user.setPassword("pass1234");

        List<Transaction> actual = transRepo.findByTransactiontype(user, 1);

        List<Transaction> expectedTrans = new ArrayList<>();


        Transaction expected = new Transaction();

        Category category = catrepo.getById(1);

        expected.setId(1);
        expected.setAmount(1840.73);
        expected.setDescription("Bi-weekly wage direct deposit");
        expected.setCategory(category);
        expected.setUser(user);
        expected.setShared(false);

        expectedTrans.add(expected);

        Assertions.assertEquals(expectedTrans, actual);

    }

     */
}
