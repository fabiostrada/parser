package it.allitude.parser.utility.model;

import java.util.Objects;

public class Filter {

    private String parameter;
    private String value;
    private Operation operation;

    public String getParameter() {
        return parameter;
    }

    public Filter setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Filter setValue(String value) {
        this.value = value;
        return this;
    }

    public Operation getOperation() {
        return operation;
    }

    public Filter setOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(parameter, filter.parameter) && Objects.equals(value, filter.value) && operation == filter.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter, value, operation);
    }
}
