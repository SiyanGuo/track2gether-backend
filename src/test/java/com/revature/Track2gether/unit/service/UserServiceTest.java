package com.revature.Track2gether.unit.service;


import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.UsersRepository;
import com.revature.Track2gether.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Test
    public void test_getUserById() throws EntityNotFoundException, BadParameterException {
        // Arrange
        Users fakeUser = new Users();
        fakeUser.setId(88);
        fakeUser.setFirstname("testFirstName");
        fakeUser.setLastname("testLastName");
        fakeUser.setPassword("testPassword");
        fakeUser.setEmail("testemail@email.com");
        fakeUser.setSpouseId(fakeUser);
        when(userService.getUserById(eq(88))).thenReturn(fakeUser);

        // Act
        Users actual = userService.getUserById(88);

        //Assert

        Users expected = new Users();
        expected.setId(88);
        expected.setFirstname("testFirstName");
        expected.setLastname("testLastName");
        expected.setPassword("testPassword");
        expected.setEmail("testemail@email.com");
        expected.setSpouseId(expected);

        Assertions.assertEquals(expected, actual);

    }

}
