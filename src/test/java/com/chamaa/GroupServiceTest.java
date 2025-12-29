package com.chamaa;

import com.chamaa.entities.Group;
import com.chamaa.entities.User;
import com.chamaa.repositories.GroupRepository;
import com.chamaa.repositories.UserRepository;
import com.chamaa.services.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GroupService.class)
@ActiveProfiles("test")
class GroupServiceTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    private Group testGroup;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("creator@example.com");
        testUser.setPassword("password");
        testUser.setFirstName("Jane");
        testUser.setLastName("Doe");
        userRepository.save(testUser);

        testGroup = new Group();
        testGroup.setName("Test Group");
        testGroup.setDescription("Test Group Description");
        testGroup.setCreator(testUser);
    }

    @Test
    void testCreateGroup() {
        Group created = groupService.createGroup(testGroup);
        assertNotNull(created.getId());
        assertEquals("Test Group", created.getName());
    }

    @Test
    void testGetGroupById() {
        Group created = groupService.createGroup(testGroup);
        var found = groupService.getGroupById(created.getId());
        assertTrue(found.isPresent());
    }

    @Test
    void testGetGroupsByCreatorId() {
        groupService.createGroup(testGroup);
        var groups = groupService.getGroupsByCreatorId(testUser.getId());
        assertFalse(groups.isEmpty());
    }
}
