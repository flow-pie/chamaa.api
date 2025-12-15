package com.chamaa;

import com.chamaa.entities.User;
import com.chamaa.repositories.UserRepository;
import com.chamaa.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({UserService.class, BCryptPasswordEncoder.class})
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
    }

    @Test
    void testCreateUser() {
        User created = userService.createUser(testUser);
        assertNotNull(created.getId());
        assertEquals("test@example.com", created.getEmail());
    }

    @Test
    void testGetUserById() {
        User created = userService.createUser(testUser);
        var found = userService.getUserById(created.getId());
        assertTrue(found.isPresent());
    }

    @Test
    void testGetUserByEmail() {
        userService.createUser(testUser);
        var found = userService.getUserByEmail("test@example.com");
        assertTrue(found.isPresent());
    }
}
