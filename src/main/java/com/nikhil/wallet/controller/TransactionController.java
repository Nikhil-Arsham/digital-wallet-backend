package com.nikhil.wallet.controller;

import com.nikhil.wallet.dto.TransactionResponse;
import com.nikhil.wallet.service.WalletService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final WalletService walletService;

    public TransactionController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(
            Authentication authentication) {

        String email = authentication.getName();

        List<TransactionResponse> transactions =
                walletService.getTransactionHistory(email);

        return ResponseEntity.ok(transactions);
    }
}
