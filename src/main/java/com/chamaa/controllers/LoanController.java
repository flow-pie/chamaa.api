package com.chamaa.controllers;

import com.chamaa.entities.Loan;
import com.chamaa.services.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(loan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/borrower/{borrowerId}")
    public ResponseEntity<List<Loan>> getLoansByBorrower(@PathVariable Long borrowerId) {
        return ResponseEntity.ok(loanService.getLoansByBorrowerId(borrowerId));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Loan>> getLoansByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(loanService.getLoansByGroupId(groupId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable String status) {
        return ResponseEntity.ok(loanService.getLoansByStatus(status));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.approveLoan(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Loan> rejectLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.rejectLoan(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
