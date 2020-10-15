package com.adrien.bam.model;

import java.time.LocalDateTime;

public class Operation {
    private final LocalDateTime date;
    private final OperationType operation;
    private final int amount;
    private final int balance;

    public Operation(LocalDateTime date, OperationType operation, int amount, int balance) {
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    public OperationType getOperation() {
        return operation;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }
}
