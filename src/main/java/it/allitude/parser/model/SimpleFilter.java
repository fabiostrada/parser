package it.allitude.parser.model;

public class SimpleFilter<V,O> {

    private V value;
    private O operation;

    public V getValue() {
        return value;
    }

    public<K extends SimpleFilter<V, O>> K setValue(V value) {
        this.value = value;
        return (K)this;
    }

    public O getOperation() {
        return operation;
    }

    public<K extends SimpleFilter<V, O>> K setOperation(O operation) {
        this.operation = operation;
        return (K)this;
    }
}
