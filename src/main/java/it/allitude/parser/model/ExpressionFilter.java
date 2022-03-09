package it.allitude.parser.model;

import it.allitude.parser.model.operation.LogicalOperation;

import java.util.List;
import java.util.Objects;

public class ExpressionFilter<V,O> {

    private List<SimpleFilter<V,O>> array;
    private LogicalOperation logicalOperation = LogicalOperation.and;

    public List<SimpleFilter<V, O>> getArray() {
        return array;
    }

    public ExpressionFilter<V, O> setArray(List<SimpleFilter<V, O>> array) {
        this.array = array;
        return this;
    }

    public LogicalOperation getLogicalOperation() {
        return logicalOperation;
    }

    public ExpressionFilter<V, O> setLogicalOperation(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressionFilter<?, ?> that = (ExpressionFilter<?, ?>) o;
        return Objects.equals(array, that.array) && logicalOperation == that.logicalOperation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(array, logicalOperation);
    }
}
