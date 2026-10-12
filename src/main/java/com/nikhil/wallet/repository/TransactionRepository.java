package com.nikhil.wallet.repository;

import com.nikhil.wallet.entity.Transaction;
import com.nikhil.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderWalletOrReceiverWalletOrderByCreatedAtDesc(
            Wallet senderWallet,
            Wallet receiverWallet
    );
}
