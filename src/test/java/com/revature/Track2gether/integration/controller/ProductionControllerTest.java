package com.revature.Track2gether.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Track2gether.dto.LoginDTO;
import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.dto.UserJwtDto;
import com.revature.Track2gether.dto.UserResponseDTO;
import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.CategoryRepository;
import com.revature.Track2gether.service.*;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService usersService;

    @Autowired
    private TransactionService transService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CategoryRepository catRepo;
    Transactiondto tdto = new Transactiondto();
    UserResponseDTO udto = new UserResponseDTO();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    /*
    @Test
    public void test_loginEndpoint_positive() throws Exception {

        LoginDTO ldto = new LoginDTO();
        ldto.setEmail("jshim@gmail.com");
        ldto.setPassword("pass1234");
        String jsonDto = mapper.writeValueAsString(ldto);

        UserResponseDTO expected = new UserResponseDTO();
        expected.setId(1);
        expected.setFirstName("Jiwon");
        expected.setLastName("Shim");
        expected.setEmail("jshim@gmail.com");

        String expectedJson = mapper.writeValueAsString(expected);

        this.mockMvc.perform(
                        post("/login").
                                contentType(MediaType.APPLICATION_JSON).
                                content(jsonDto))
                .andExpect(status().is(200)) // 200 status code
                .andExpect(content().json(expectedJson)) // User object as JSON in the response body
                // token response header contains the correct JWT
                .andExpect(header().string("token", "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2R0byI6IntcInVzZXJJZFwiOjEsXCJmaXJzdE5hbWVcIjpcIkppd29uXCIsXCJsYXN0TmFtZVwiOlwiU2hpbVwiLFwiZW1haWxcIjpcImpzaGltQGdtYWlsLmNvbVwiLFwic3BvdXNlSWRcIjpudWxsfSJ9.w2Fgh9HQGz7afPA7MKsQCE4Jtp-35sEiFOykAiwQ_pe9LD0bgfYsQfDqJWN9nGhEcWrO4i_qgXoVxWSA5CyewA"));
    }

    @Test
    public void test_loginEndpoint_invalidCredentials() throws Exception, NullPointerException {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("invalid");
        dto.setPassword("invalid");
        String jsonDto = mapper.writeValueAsString(dto);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andExpect(status().is(401))
                .andExpect(content().string("Invalid email and/or password"));
    }

    @Test
    public void test_addTransaction_positive() throws Exception {


        Users user = authService.login("jshim@gmail.com", "pass1234");

        String jwt = jwtService.createJwt(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", jwt);

        tdto.setAmount(1840.73);
        tdto.setDate("03/09/2022");
        tdto.setDescription("Bi-weekly wage direct deposit");
        tdto.setCategoryid(1);

        String jsonDto = mapper.writeValueAsString(tdto);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/users/{userid}/transaction", 1).header("Authorization", responseHeaders)
                .content(jsonDto).contentType(MediaType.APPLICATION_JSON);

        Date dt = df.parse(tdto.getDate());
        Category category = catRepo.getById(tdto.getCategoryid());

        Transaction newTrans = new Transaction();
        newTrans.setId(5);
        newTrans.setAmount(tdto.getAmount());
        newTrans.setDate(dt);
        newTrans.setCategory(category);
        newTrans.setDescription(tdto.getDescription());
        newTrans.setUser(user);
        newTrans.setShared(false);

        Transactiondto expected = new Transactiondto();

        expected = transService.addTransaction(newTrans);

        System.out.println(expected);

        String expectedJson = mapper.writeValueAsString(expected);

        System.out.println(expectedJson);

        this.mockMvc.perform(builder).andExpect(status().is(200)).andExpect(content().json(expectedJson));
    }

    @Test
    public void test_getTransactionByUserId_positive() throws Exception {

        Users users = authService.login("jshim@gmail.com", "pass1234");

        String jwt = jwtService.createJwt(users);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", jwt);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/users/{userid}/transactions", 1).header("Authorization", responseHeaders)
                .contentType(MediaType.APPLICATION_JSON);

        Transactiondto expected = new Transactiondto();

        expected.setId(1);
        expected.setAmount(1840.73);
        expected.setDate("03/09/2022");
        expected.setCategoryid(1);
        expected.setDescription("Bi-weekly wage direct deposit");
        expected.setCategoryname("salary");
        expected.setCategoryType("income");
        expected.setUserid(1);
        expected.setFirstname("Jiwon");
        expected.setLastname("Shim");
        expected.setEmail("jshim@gmail.com");
        expected.setShared(false);

        Transactiondto expected1 = new Transactiondto();

        expected1.setId(2);
        expected1.setAmount(213.87);
        expected1.setDate("03/13/2022");
        expected1.setCategoryid(6);
        expected1.setDescription("Grocery Shopping");
        expected1.setCategoryname("food");
        expected1.setCategoryType("expenses");
        expected1.setUserid(1);
        expected1.setFirstname("Jiwon");
        expected1.setLastname("Shim");
        expected1.setEmail("jshim@gmail.com");
        expected1.setShared(false);

        List<Transactiondto> expectedTransactions = new ArrayList<>();

        expectedTransactions.add(expected);
        expectedTransactions.add(expected1);

        String expectedJson = mapper.writeValueAsString(expectedTransactions);


        this.mockMvc.perform(
                        builder)
                .andExpect(status().is(200)).andExpect(content().json(expectedJson));
    }

     */


            /*
    @Test
    public void test_getTransactionByTranstype_positive() throws Exception {

    }

    @Test
    public void test_updateTransaction_positive() throws Exception {

    }

    @Test
    public void test_getAllCategory_positive() throws Exception {

    }

    @Test
    public void test_deleteTransaction_positive() throws Exception {

    }

             */
}





