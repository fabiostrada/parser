package it.allitude.parser.model.operation;

import java.util.stream.Stream;

public enum LogicalOperation {

    and("_and_"),
    or("_or_");

    private String value;

    LogicalOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LogicalOperation logicalOperationOf(String value) {
        return Stream.of(LogicalOperation.values())
                .filter(logicalOperation -> logicalOperation.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Logical Operation with value " + value + " not found"));
    }
}
