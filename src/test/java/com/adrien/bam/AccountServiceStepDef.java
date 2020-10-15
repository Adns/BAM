package com.adrien.bam;

import com.adrien.bam.model.CustomerAction;
import com.adrien.bam.model.Operation;
import com.adrien.bam.model.OperationType;
import com.adrien.bam.services.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberIntegrationTest.class)
@ContextConfiguration(classes = BamApplication.class, loader = SpringBootContextLoader.class)
public class AccountServiceStepDef {

    @Autowired
    private AccountService accountService;

    private String currentBankCustomer;
    private List<Operation> currentOperations;
    private Exception exception;

    @Given("I'm a bank customer {string}")
    public void isBankCustomer(String name) {
        if (accountService.isBankCustomer(name)) {
            currentBankCustomer = name;
        } else {
            Assert.fail();
        }
    }

    @Given("I'm not a bank customer {string}")
    public void isNotBankCustomer(String name) {
        if (accountService.isBankCustomer(name)) {
            Assert.fail();
        } else {
            currentBankCustomer = name;
        }
    }

    @When("I make deposit with {int}")
    public void iCallDepositWith(int amount) {
        try {
            accountService.action(new CustomerAction(currentBankCustomer, amount, OperationType.DEPOSIT));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("I receive the new amount of my account {int}")
    public void iReceiveTheNewAmountOfMyAccount(int newAmount) {
        Assert.assertEquals(newAmount, accountService.getAmount(currentBankCustomer));
    }

    @When("I make a withdrawal with {int}")
    public void iMakeAWithdrawalWith(int amount) {
        try {
            accountService.action(new CustomerAction(currentBankCustomer, amount, OperationType.WITHDRAWAL));
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I ask for my history")
    public void iAskForMyHistory() {
        currentOperations = accountService.getOperations(currentBankCustomer);
    }

    @Then("I receive the history of my operations {string}")
    public void iReceiveTheHistoryOfMyOperations(String operations) {
        List<OperationType> operationTypeList = Arrays.stream(operations.split(","))
                .map(OperationType::from)
                .collect(Collectors.toList());
        Assert.assertEquals(operationTypeList.size(), currentOperations.size());
        for (int i = 0; i < operationTypeList.size(); i++) {
            Assert.assertEquals(operationTypeList.get(i), currentOperations.get(i).getOperation());
        }
    }

    @Then("I receive Exception {string}")
    public void iReceiveException(String message) {
        Assert.assertNotNull(exception);
        Assert.assertEquals(message, exception.getMessage());
    }
}
