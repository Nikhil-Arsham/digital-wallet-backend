package com.nikhil.wallet.service;

import com.nikhil.wallet.entity.Transaction;
import com.nikhil.wallet.entity.User;
import com.nikhil.wallet.entity.Wallet;
import com.nikhil.wallet.repository.TransactionRepository;
import com.nikhil.wallet.repository.UserRepository;
import com.nikhil.wallet.repository.WalletRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public AdminService(
            UserRepository userRepository,
            WalletRepository walletRepository,
            TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
