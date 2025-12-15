package com.chamaa.services;

import com.chamaa.entities.Wallet;
import com.chamaa.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWalletById(Long id) {
        return walletRepository.findById(id);
    }

    public Optional<Wallet> getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    public Wallet updateBalance(Long walletId, Double amount) {
        return walletRepository.findById(walletId).map(wallet -> {
            wallet.setBalance(wallet.getBalance() + amount);
            return walletRepository.save(wallet);
        }).orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
