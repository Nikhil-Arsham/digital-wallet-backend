package com.nikhil.wallet.dto;

import com.nikhil.wallet.enums.TransactionStatus;
import com.nikhil.wallet.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private Long senderWalletId;
    private Long receiverWalletId;
    private LocalDateTime createdAt;

    public TransactionResponse(
            Long id,
            BigDecimal amount,
            TransactionType type,
            TransactionStatus status,
            Long senderWalletId,
            Long receiverWalletId,
            LocalDateTime createdAt) {

        this.id = id;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.senderWalletId = senderWalletId;
        this.receiverWalletId = receiverWalletId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public Long getSenderWalletId() {
        return senderWalletId;
    }

    public Long getReceiverWalletId() {
        return receiverWalletId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
