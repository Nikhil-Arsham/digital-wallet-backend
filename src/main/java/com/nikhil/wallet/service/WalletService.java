package com.nikhil.wallet.service;

import com.nikhil.wallet.dto.TransactionResponse;
import com.nikhil.wallet.entity.Transaction;
import com.nikhil.wallet.entity.User;
import com.nikhil.wallet.entity.Wallet;
import com.nikhil.wallet.enums.TransactionStatus;
import com.nikhil.wallet.enums.TransactionType;
import com.nikhil.wallet.exception.BadRequestException;
import com.nikhil.wallet.exception.ResourceNotFoundException;
import com.nikhil.wallet.repository.TransactionRepository;
import com.nikhil.wallet.repository.UserRepository;
import com.nikhil.wallet.repository.WalletRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(
            WalletRepository walletRepository,
            UserRepository userRepository,
            TransactionRepository transactionRepository) {

        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet getWalletByUserEmail(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wallet not found"
                        ));
    }

    @Transactional
    public Wallet addMoney(
            String email,
            BigDecimal amount) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Wallet wallet = walletRepository
                .findWithLockByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wallet not found"
                        ));

        wallet.setBalance(
                wallet.getBalance().add(amount)
        );

        walletRepository.save(wallet);

        Transaction transaction = new Transaction();

        transaction.setReceiverWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.ADD_MONEY);
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);

        return wallet;
    }

    @Transactional
    public Wallet transfer(
            String senderEmail,
            Long receiverId,
            BigDecimal amount) {

        User sender = userRepository.findByEmail(senderEmail);

        if (sender == null) {
            throw new ResourceNotFoundException(
                    "Sender not found"
            );
        }

        if (sender.getId().equals(receiverId)) {
            throw new BadRequestException(
                    "You cannot transfer money to yourself"
            );
        }

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Receiver not found"
                        ));

        Wallet senderWallet = walletRepository
                .findWithLockByUser(sender)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sender wallet not found"
                        ));

        Wallet receiverWallet = walletRepository
                .findWithLockByUserId(receiverId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Receiver wallet not found"
                        ));

        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new BadRequestException(
                    "Insufficient wallet balance"
            );
        }

        senderWallet.setBalance(
                senderWallet.getBalance().subtract(amount)
        );

        receiverWallet.setBalance(
                receiverWallet.getBalance().add(amount)
        );

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        Transaction transaction = new Transaction();

        transaction.setSenderWallet(senderWallet);
        transaction.setReceiverWallet(receiverWallet);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);

        return senderWallet;
    }

    public List<TransactionResponse> getTransactionHistory(
            String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException(
                    "User not found"
            );
        }

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wallet not found"
                        ));

        return transactionRepository
                .findBySenderWalletOrReceiverWalletOrderByCreatedAtDesc(
                        wallet,
                        wallet
                )
                .stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getType(),
                        transaction.getStatus(),
                        transaction.getSenderWallet() != null
                                ? transaction.getSenderWallet().getId()
                                : null,
                        transaction.getReceiverWallet() != null
                                ? transaction.getReceiverWallet().getId()
                                : null,
                        transaction.getCreatedAt()
                ))
                .toList();
    }
}
