package com.example.galerka;

import com.example.galerka.models.User;
import com.example.galerka.models.enums.Role;
import com.example.galerka.repositories.UserRepository;
import com.example.galerka.services.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestOnCreate {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    public void addUser(){
        User user = new User();
        user.setEmail("qwerty@mail.ru");

        boolean isUserCreated = userService.createUser(user);
        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ROLE_ADMIN)));

        Mockito.verify(userRepository , Mockito.times(1)).save(user);
    }


    @Test
    public void addUserFailTest() {
        User user = new User();

        user.setEmail("John@mail.ru");

        Mockito.doReturn(new User()).when(userRepository).findByEmail("John@mail.ru");
        boolean isUserCreated = userService.createUser(user);
        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository , Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }
}