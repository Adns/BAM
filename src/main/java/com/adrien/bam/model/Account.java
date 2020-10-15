package com.adrien.bam.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String name;
    private final List<Operation> operations;
    private int amount;

    public Account(String name) {
        this.name = name;
        this.amount = 0;
        this.operations = new ArrayList<>();
    }

    public Account(String name, int initialAmount) {
        this.name = name;
        this.amount = initialAmount;
        this.operations = new ArrayList<>();
    }

    public void deposit(int amount) {
        this.amount += amount;
        addOperation(OperationType.DEPOSIT, amount);
    }

    public void withdrawal(int amount) {
        this.amount -= amount;
        addOperation(OperationType.WITHDRAWAL, amount);
    }

    private void addOperation(OperationType operation, int amount) {
        operations.add(new Operation(LocalDateTime.now(), operation, amount, this.amount));
    }

    public int getAmount() {
        return amount;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
