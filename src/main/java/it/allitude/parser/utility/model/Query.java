package it.allitude.parser.utility.model;

import java.util.List;
import java.util.Objects;

public class Query {

    private List<Filter> filters;
    private LogicalOperation logicalOperation = LogicalOperation.AND;

    public List<Filter> getFilters() {
        return filters;
    }

    public Query setFilters(List<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public LogicalOperation getLogicalOperation() {
        return logicalOperation;
    }

    public Query setLogicalOperation(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(filters, query.filters) && logicalOperation == query.logicalOperation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filters, logicalOperation);
    }
}
