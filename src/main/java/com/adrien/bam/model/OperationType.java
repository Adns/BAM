package com.adrien.bam.model;

import java.util.Arrays;

public enum OperationType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private String name;

    OperationType(String name) {
        this.name = name;
    }

    public static OperationType from(String operationName) {
        return Arrays.stream(OperationType.values())
                .filter(ot -> ot.getName().equalsIgnoreCase(operationName))
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
