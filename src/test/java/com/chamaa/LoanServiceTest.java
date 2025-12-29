package com.chamaa;

import com.chamaa.entities.*;
import com.chamaa.repositories.*;
import com.chamaa.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(LoanService.class)
@ActiveProfiles("test")
class LoanServiceTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private LoanService loanService;

    private Loan testLoan;
    private User borrower;
    private Group group;

    @BeforeEach
    void setUp() {
        borrower = new User();
        borrower.setEmail("borrower@example.com");
        borrower.setPassword("password");
        borrower.setFirstName("Bob");
        borrower.setLastName("Smith");
        userRepository.save(borrower);

        User creator = new User();
        creator.setEmail("creator@example.com");
        creator.setPassword("password");
        creator.setFirstName("Alice");
        creator.setLastName("Johnson");
        userRepository.save(creator);

        group = new Group();
        group.setName("Loan Group");
        group.setCreator(creator);
        groupRepository.save(group);

        testLoan = new Loan();
        testLoan.setBorrower(borrower);
        testLoan.setGroup(group);
        testLoan.setAmount(1000.0);
        testLoan.setDurationInMonths(12);
    }

    @Test
    void testCreateLoan() {
        Loan created = loanService.createLoan(testLoan);
        assertNotNull(created.getId());
        assertEquals(1000.0, created.getAmount());
    }

    @Test
    void testApproveLoan() {
        Loan created = loanService.createLoan(testLoan);
        Loan approved = loanService.approveLoan(created.getId());
        assertEquals("APPROVED", approved.getStatus());
    }

    @Test
    void testRejectLoan() {
        Loan created = loanService.createLoan(testLoan);
        Loan rejected = loanService.rejectLoan(created.getId());
        assertEquals("REJECTED", rejected.getStatus());
    }
}
