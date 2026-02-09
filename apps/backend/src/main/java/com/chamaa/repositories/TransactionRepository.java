package com.chamaa.repositories;

import com.chamaa.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromWalletId(Long walletId);
    List<Transaction> findByToWalletId(Long walletId);
    List<Transaction> findByGroupId(Long groupId);
}
