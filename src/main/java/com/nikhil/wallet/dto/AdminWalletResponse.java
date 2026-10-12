package com.nikhil.wallet.dto;

import java.math.BigDecimal;

public class AdminWalletResponse {

    private Long walletId;
    private Long userId;
    private String userName;
    private String userEmail;
    private BigDecimal balance;

    public AdminWalletResponse(
            Long walletId,
            Long userId,
            String userName,
            String userEmail,
            BigDecimal balance) {

        this.walletId = walletId;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.balance = balance;
    }

    public Long getWalletId() {
        return walletId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
