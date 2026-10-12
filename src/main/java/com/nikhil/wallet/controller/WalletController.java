package com.nikhil.wallet.controller;

import com.nikhil.wallet.dto.AddMoneyRequest;
import com.nikhil.wallet.dto.TransferRequest;
import com.nikhil.wallet.dto.WalletResponse;
import com.nikhil.wallet.entity.Wallet;
import com.nikhil.wallet.service.WalletService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<WalletResponse> getMyWallet(
            Authentication authentication) {

        String email = authentication.getName();

        Wallet wallet = walletService.getWalletByUserEmail(email);

        WalletResponse response = new WalletResponse(
                wallet.getId(),
                wallet.getBalance()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-money")
    public ResponseEntity<WalletResponse> addMoney(
            @Valid @RequestBody AddMoneyRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        Wallet wallet = walletService.addMoney(
                email,
                request.getAmount()
        );

        WalletResponse response = new WalletResponse(
                wallet.getId(),
                wallet.getBalance()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer")
    public ResponseEntity<WalletResponse> transfer(
            @Valid @RequestBody TransferRequest request,
            Authentication authentication) {

        String senderEmail = authentication.getName();

        Wallet senderWallet = walletService.transfer(
                senderEmail,
                request.getReceiverId(),
                request.getAmount()
        );

        WalletResponse response = new WalletResponse(
                senderWallet.getId(),
                senderWallet.getBalance()
        );

        return ResponseEntity.ok(response);
    }
}
