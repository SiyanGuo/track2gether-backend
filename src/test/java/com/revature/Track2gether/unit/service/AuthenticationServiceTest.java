package com.revature.Track2gether.unit.service;


import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.UsersRepository;
import com.revature.Track2gether.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UsersRepository userRepo;

    @InjectMocks

    private AuthenticationService authService;

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void test_login_positive_validEmailAndPassword_noWhitespace() throws
            FailedLoginException, BadParameterException {
        // Arrange
        Users fakeUser = new Users();
        fakeUser.setId(99);
        fakeUser.setFirstname("myFirstName");
        fakeUser.setLastname("myLastName");
        fakeUser.setPassword("myPassword");
        fakeUser.setEmail("myemail@email.com");

        Users u = new Users();
        u.setId(100);
        u.setFirstname("yourFirstName");
        u.setLastname("yourLastName");
        u.setPassword("yourPassword");
        u.setEmail("youremail@email.com");
        u.setSpouseId(fakeUser);

        fakeUser.setSpouseId(u);

        when(userRepo.findByEmailAndPassword(eq("myemail@email.com"),
                eq("myPassword"))).thenReturn(fakeUser);

        // Act
        Users actual = authService.login("myemail@email.com", "myPassword");

        // Assert
        Users expected = new Users();
        expected.setId(99);
        expected.setFirstname("myFirstName");
        expected.setLastname("myLastName");
        expected.setPassword("myPassword");
        expected.setEmail("myemail@email.com");
        expected.setPassword("myPassword");

        Users p = new Users();
        p.setId(100);
        p.setFirstname("yourFirstName");
        p.setLastname("yourLastName");
        p.setPassword("yourPassword");
        p.setEmail("youremail@email.com");
        p.setSpouseId(expected);

        expected.setSpouseId(p);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void test_login_positive_validEmailAndPassword_whitespace() throws
            FailedLoginException, BadParameterException {
        // Arrange
        Users fakeUser = new Users();
        fakeUser.setId(99);
        fakeUser.setFirstname("myFirstName");
        fakeUser.setLastname("myLastName");
        fakeUser.setPassword("myPassword");
        fakeUser.setEmail("myemail@email.com");

        Users u = new Users();
        u.setId(100);
        u.setFirstname("yourFirstName");
        u.setLastname("yourLastName");
        u.setPassword("yourPassword");
        u.setEmail("youremail@email.com");
        u.setSpouseId(fakeUser);

        fakeUser.setSpouseId(u);

        when(userRepo.findByEmailAndPassword(eq("myemail@email.com"),
                eq("myPassword"))).thenReturn(fakeUser);

        // Act
        Users actual = authService.login("     myemail@email.com     ", "     myPassword     ");

        // Assert
        Users expected = new Users();
        expected.setId(99);
        expected.setFirstname("myFirstName");
        expected.setLastname("myLastName");
        expected.setPassword("myPassword");
        expected.setEmail("myemail@email.com");
        expected.setPassword("myPassword");

        Users p = new Users();
        p.setId(100);
        p.setFirstname("yourFirstName");
        p.setLastname("yourLastName");
        p.setPassword("yourPassword");
        p.setEmail("youremail@email.com");
        p.setSpouseId(expected);

        expected.setSpouseId(p);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void test_login_invalidEmailAndPassword() throws FailedLoginException {
        // Arrange

        // Act + Assert
        Assertions.assertThrows(FailedLoginException.class, () -> {
            authService.login("invalid", "invalid");
        });
    }
}