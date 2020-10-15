package com.adrien.bam.model;

public class CustomerAction {
    String name;
    int amount;
    OperationType operationType;

    public CustomerAction(String name, int amount, OperationType operationType) {
        this.name = name;
        this.amount = amount;
        this.operationType = operationType;
    }

    public CustomerAction() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
