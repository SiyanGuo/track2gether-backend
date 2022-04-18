package com.revature.Track2gether.service;

import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Transactiontype;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.CategoryRepository;
import com.revature.Track2gether.repositories.TransactionRepository;
import com.revature.Track2gether.repositories.UsersRepository;
import io.jsonwebtoken.lang.Assert;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.security.auth.login.FailedLoginException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImpTest {
    @Mock
    private TransactionRepository transrepo;
    private CategoryRepository catRepo;


    @InjectMocks
    private TransactionServiceImp transserviceImp;

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    Users fakeuser = new Users();
    Category fakecat = new Category();
    Transaction faketransaction = new Transaction();
    Transactiondto fakedto = new Transactiondto();
    List<Transaction> fakeTransactions = new ArrayList<>();
    List<Transactiondto> fakeTransDto = new ArrayList<>();
    //Transactiontype fakeTransType = new Transactiontype();


    @BeforeEach
    public void setup() throws ParseException {
        // Users fakeuser = new Users();
        fakeuser.setId(1);
        fakeuser.setFirstname("lixy");
        fakeuser.setLastname("mat");
        fakeuser.setEmail("abc.j8@gmail.com");
        fakeuser.setPassword("lixy123");

        // Category fakecat = new Category();
        fakecat.setId(6);
        fakecat.setCategoryname("food");
        fakecat.setTranstype(new Transactiontype(2, "expenses"));

        /*
        // Fake Transaction Type
        fakeTransType.setId(2);
        fakeTransType.setType("expenses");
         */

        //  Transaction faketransaction = new Transaction();
        faketransaction.setId(1);
        faketransaction.setDate(df.parse("12/12/2022"));
        faketransaction.setAmount(2000);
        faketransaction.setDescription("expense added");
        faketransaction.setShared(true);
        faketransaction.setUser(fakeuser);
        faketransaction.setCategory(fakecat);

        // Transactiondto fakedto = new Transactiondto();
        fakedto.setId(1);
        fakedto.setEmail("abc.j8@gmail.com");
        fakedto.setLastname("mat");
        fakedto.setFirstname("lixy");
        fakedto.setShared(true);
        fakedto.setAmount(2000);
        fakedto.setCategoryType(fakecat.getTranstype().getType());
        fakedto.setCategoryname("food");
        fakedto.setUserid(1);
        fakedto.setCategoryid(6);
        fakedto.setDescription("expense added");
        fakedto.setDate("12/12/2022");

        // Fake Transaction List for fakeuser
        fakeTransactions.add(new Transaction (1, 2000, df.parse("12/12/2022"),
                "expense added", true, fakecat, fakeuser));
        fakeTransactions.add(new Transaction(2, 300, df.parse("12/01/2022"),
                "grocery shopping", false, fakecat, fakeuser));

        // Fake Transaction DTO List for fakeuser
        fakeTransDto.add(new Transactiondto(1, 2000, "12/12/2022", 6,
                "expense added", "food", fakecat.getTranstype().getType(), 1, "lixy",
                "mat", "abc.j8@gmail.com", true));
        fakeTransDto.add(new Transactiondto(2, 300, "12/01/2022", 6,
                "grocery shopping", "food", fakecat.getTranstype().getType(), 1, "lixy",
                "mat", "abc.j8@gmail.com", false));



    }

    @Test
    public void addTransactionTest() throws BadParameterException {
        Mockito.lenient().when(transrepo.save(any())).thenReturn(faketransaction);

        Transactiondto actualdto = transserviceImp.addTransaction(faketransaction);
        Transactiondto expected = fakedto;
        Assertions.assertEquals(expected,actualdto);
    }
    @Test
    public void findByUserTest() {

        when(transrepo.findByUser(eq(fakeuser))).thenReturn(fakeTransactions);

        List<Transactiondto> actual = transserviceImp.findByUser(fakeuser);
        List<Transactiondto> expected = new ArrayList<>(fakeTransDto);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void findByTransactiontype() throws BadParameterException {

        when(transrepo.findByTransactiontype(eq(fakeuser), eq(fakecat.getTranstype().getId()))).thenReturn(fakeTransactions);

        List<Transactiondto> actual = transserviceImp.findByTransactiontype(fakeuser, fakecat.getTranstype().getId());
        List<Transactiondto> expected = new ArrayList<>(fakeTransDto);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void findByTransactionType_negative() throws BadParameterException {

        Assertions.assertThrows(BadParameterException.class, () -> {
            transserviceImp.findByTransactiontype(fakeuser, 3);
        });

    }

    @Test
    public void findByTransactions() {

        when(transrepo.findByTransactions(eq(2022), eq(12), eq(fakeuser))).thenReturn(fakeTransactions);

        List<Transactiondto> actual = transserviceImp.findByTransactions(2022, 12, fakeuser);
        List<Transactiondto> expected = new ArrayList<>(fakeTransDto);

        Assertions.assertEquals(expected, actual);

    }


    @Test
    void updateTransaction() throws BadParameterException {
       /* when(transrepo.findById(1).get()).thenReturn(faketransaction);
        Mockito.lenient().when(transrepo.save(faketransaction)).thenReturn(faketransaction);
        Transactiondto actualdto = transserviceImp.updateTransaction(faketransaction);
        Transactiondto expected = fakedto;
        Assertions.assertEquals(expected, actualdto); */
    }

    @Test
    void deleteTransactionById() {
      /*  when(transrepo.findById(111).get()).thenReturn(faketransaction);
        when(transrepo.delete()).getMock(tr))
        Assertions.assertEquals(transrepo.delete(faketransaction),transserviceImp.deleteTransactionById(1));*/

    }

}