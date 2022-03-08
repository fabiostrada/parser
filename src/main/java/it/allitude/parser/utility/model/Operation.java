package it.allitude.parser.utility.model;

import java.util.stream.Stream;

public enum Operation {

    EQUAL("="),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
    CONTAINS("contains");

    private String value;

    Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Operation from(String operationValue) {
        return Stream.of(Operation.values())
                     .filter(operation -> operation.value.equals(operationValue))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("operation value not found"));
    }
}
