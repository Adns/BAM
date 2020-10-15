package com.adrien.bam.services;

import com.adrien.bam.model.Account;
import com.adrien.bam.model.CustomerAction;
import com.adrien.bam.model.Operation;
import com.adrien.bam.model.OperationType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountService {

    Map<String, Account> accounts = new HashMap<>();

    public AccountService() {
        this.accounts.put("John Doe", new Account("John Doe"));
        this.accounts.put("Jane Doe", new Account("Jane Doe"));
        this.accounts.put("Jacques Bond", new Account("Jacques Bond"));
    }

    public AccountService(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Operation action(CustomerAction customerAction) throws IllegalArgumentException {
        String name = customerAction.getName();
        int amount = customerAction.getAmount();
        OperationType operationType = customerAction.getOperationType();

        if (!this.isBankCustomer(name)) {
            throw new IllegalArgumentException("Customer " + name + " is not a bank customer");
        }

        if (operationType == OperationType.DEPOSIT) {
            deposit(name, amount);
        } else if (operationType == OperationType.WITHDRAWAL) {
            withdrawal(name, amount);
        } else {
            throw new IllegalArgumentException("Operation " + operationType + " is not recognized");
        }

        return getLastOperation(name);
    }

    private void deposit(String name, int amount) {
        this.getAccount(name).deposit(amount);
    }

    private void withdrawal(String name, int amount) {
        this.getAccount(name).withdrawal(amount);
    }

    public boolean isBankCustomer(String name) {
        return this.accounts.containsKey(name);
    }

    public int getAmount(String name) {
        return this.getAccount(name).getAmount();
    }

    public List<Operation> getOperations(String name) {
        if (!this.isBankCustomer(name)) {
            throw new IllegalArgumentException("Customer " + name + " is not a bank customer");
        }

        return this.getAccount(name).getOperations();
    }

    public Operation getLastOperation(String name) {
        List<Operation> operations = this.getAccount(name).getOperations();
        int size = operations.size();
        if (size == 0) {
            return null;
        }
        return operations.get(size - 1);
    }

    private Account getAccount(String name) {
        return this.accounts.get(name);
    }
}
