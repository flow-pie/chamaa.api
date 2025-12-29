package com.chamaa.services;

import com.chamaa.entities.Loan;
import com.chamaa.repositories.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanService {

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    private final LoanRepository loanRepository;

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getLoansByBorrowerId(Long borrowerId) {
        return loanRepository.findByBorrowerId(borrowerId);
    }

    public List<Loan> getLoansByGroupId(Long groupId) {
        return loanRepository.findByGroupId(groupId);
    }

    public List<Loan> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }

    public Loan approveLoan(Long id) {
        return loanRepository.findById(id).map(loan -> {
            loan.setStatus("APPROVED");
            return loanRepository.save(loan);
        }).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public Loan rejectLoan(Long id) {
        return loanRepository.findById(id).map(loan -> {
            loan.setStatus("REJECTED");
            return loanRepository.save(loan);
        }).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
