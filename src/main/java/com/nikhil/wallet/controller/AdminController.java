package com.nikhil.wallet.controller;

import com.nikhil.wallet.dto.AdminUserResponse;
import com.nikhil.wallet.dto.AdminWalletResponse;
import com.nikhil.wallet.dto.TransactionResponse;
import com.nikhil.wallet.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getAllUsers() {

        List<AdminUserResponse> users =
                adminService.getAllUsers()
                        .stream()
                        .map(user -> new AdminUserResponse(
                                user.getId(),
                                user.getName(),
                                user.getEmail(),
                                user.getRole()
                        ))
                        .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/wallets")
    public ResponseEntity<List<AdminWalletResponse>> getAllWallets() {

        List<AdminWalletResponse> wallets =
                adminService.getAllWallets()
                        .stream()
                        .map(wallet -> new AdminWalletResponse(
                                wallet.getId(),
                                wallet.getUser().getId(),
                                wallet.getUser().getName(),
                                wallet.getUser().getEmail(),
                                wallet.getBalance()
                        ))
                        .toList();

        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {

        List<TransactionResponse> transactions =
                adminService.getAllTransactions()
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

        return ResponseEntity.ok(transactions);
    }
}
