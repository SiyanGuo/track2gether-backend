package com.revature.Track2gether.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Track2gether.dto.LoginDTO;
import com.revature.Track2gether.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_loginEndpoint_positive() throws Exception {

        LoginDTO ldto = new LoginDTO();
        ldto.setEmail("jshim@gmail.com");
        ldto.setPassword("myPassword");
        String jsonDto = (new ObjectMapper().writeValueAsString(ldto));

        Users expected = new Users();
        expected.setId(1);
        expected.setFirstname("Jiwon");
        expected.setLastname("shim");
        expected.setEmail("jshim@gmail.com");
        expected.setPassword("pass1234");

        Users spouse = new Users();
        spouse.setId(2);
        spouse.setFirstname("Soma");
        spouse.setLastname("Jan");
        spouse.setEmail("sjan@gmail.com");
        spouse.setPassword("pass1234");

        expected.setSpouseId(spouse);
        spouse.setSpouseId(expected);

        String expectedJson = (new ObjectMapper().writeValueAsString(expected));

        this.mockMvc.perform(
                        post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto)
        ).andExpect(status().is(200)).andExpect(content().json(expectedJson)).andExpect(header().string(
                "token", "dasdkghajweohiajshflkhajsdgwoieahjaiwoshjdkhlasdjhlksadhjalskhghhdsgewdh")
        );

    }

    @Test
    public void test_addTransaction_positive() throws Exception {

    }

    @Test
    public void test_getTransactionByUserId_positive() throws Exception {

    }

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
}
