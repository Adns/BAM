package com.adrien.bam;

import com.adrien.bam.model.Account;
import com.adrien.bam.model.Operation;
import com.adrien.bam.model.OperationType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class AccountTest {

    @Test
    public void should_increment_account_amount_when_deposit() {
        Account account = new Account("Axel");
        account.deposit(20);
        Assert.assertEquals(20, account.getAmount());
    }

    @Test
    public void should_decrement_account_amount_when_withdrawal() {
        Account account = new Account("Axel", 100);
        account.withdrawal(20);
        Assert.assertEquals(80, account.getAmount());
    }

    @Test
    public void should_create_operation_with_amount_when_deposit() {
        Account account = new Account("Axel");
        account.deposit(20);
        List<Operation> operations = account.getOperations();
        Operation last = operations.get(operations.size() - 1);
        Assert.assertEquals(OperationType.DEPOSIT, last.getOperation());
        Assert.assertEquals(20, last.getAmount());
        Assert.assertEquals(20, last.getBalance());
    }

    @Test
    public void should_create_operation_with_amount_when_withdrawal() {
        Account account = new Account("Axel", 100);
        account.withdrawal(20);
        List<Operation> operations = account.getOperations();
        Operation last = operations.get(operations.size() - 1);
        Assert.assertEquals(OperationType.WITHDRAWAL, last.getOperation());
        Assert.assertEquals(20, last.getAmount());
        Assert.assertEquals(80, last.getBalance());
    }
}
