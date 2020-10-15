package com.adrien.bam.controllers;

import com.adrien.bam.model.CustomerAction;
import com.adrien.bam.model.Operation;
import com.adrien.bam.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/action")
    public ResponseEntity<Operation> action(@RequestBody CustomerAction customerAction) {
        return ResponseEntity.ok(accountService.action(customerAction));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Operation>> history(@RequestParam String name) {
        return ResponseEntity.ok(this.accountService.getOperations(name));
    }
}
