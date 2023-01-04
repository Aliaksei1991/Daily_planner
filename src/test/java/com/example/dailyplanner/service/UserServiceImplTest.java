package com.example.dailyplanner.service;

import com.example.dailyplanner.dao.UserRepository;
import com.example.dailyplanner.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @Rollback(false)
    void deleteUser() {
        int id = 9;
        boolean isExistBeforeDelete = userService.getUser(id) != null;
        userService.deleteUser(id);
        boolean notExistAfterDelete = userService.getUser(id) != null;
        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}