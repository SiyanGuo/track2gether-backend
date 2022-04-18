package com.revature.Track2gether.integration.repository;

import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.security.auth.login.FailedLoginException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UsersRepository userRepo;


    /*
    @Test
    @Transactional
    public void test_getUserByEmailAndPassword_validCredentials() throws BadParameterException, FailedLoginException {
        // Act

        Users actual = userRepo.findByEmailAndPassword("jshim@gmail.com", "pass1234");

        Users expected = new Users();
        expected.setId(1);
        expected.setEmail("jshim@gmail.com");
        expected.setFirstname("Jiwon");
        expected.setLastname("Shim");
        expected.setPassword("pass1234");

        Assertions.assertEquals(expected, actual);
    }

     */

    @Test
    public void test_getUserByEmailAndPassword_invalidCredentials() throws BadParameterException, FailedLoginException {

        Users actual = userRepo.findByEmailAndPassword("invalid", "1234");

        Assertions.assertNull(actual);
    }


}
