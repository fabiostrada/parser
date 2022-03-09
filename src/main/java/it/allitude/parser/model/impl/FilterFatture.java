package it.allitude.parser.model.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.allitude.parser.model.ExpressionFilter;
import it.allitude.parser.model.StringFilter;
import it.allitude.parser.model.operation.DateOperation;
import it.allitude.parser.model.operation.LogicalOperation;
import it.allitude.parser.model.operation.NumberOperation;

import java.time.LocalDate;
import java.util.Objects;

public class FilterFatture {

    private ExpressionFilter<LocalDate, DateOperation> dataRicezione;
    private StringFilter destinatario;
    private ExpressionFilter<Long, NumberOperation> importo;
    private LogicalOperation logicalOperation = LogicalOperation.and;

    public ExpressionFilter<LocalDate, DateOperation> getDataRicezione() {
        return dataRicezione;
    }

    public FilterFatture setDataRicezione(ExpressionFilter<LocalDate, DateOperation> dataRicezione) {
        this.dataRicezione = dataRicezione;
        return this;
    }

    public StringFilter getDestinatario() {
        return destinatario;
    }

    public FilterFatture setDestinatario(StringFilter destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public ExpressionFilter<Long, NumberOperation> getImporto() {
        return importo;
    }

    public FilterFatture setImporto(ExpressionFilter<Long, NumberOperation> importo) {
        this.importo = importo;
        return this;
    }

    public LogicalOperation getLogicalOperation() {
        return logicalOperation;
    }

    public FilterFatture setLogicalOperation(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterFatture that = (FilterFatture) o;
        return Objects.equals(dataRicezione, that.dataRicezione) && Objects.equals(destinatario, that.destinatario) && Objects.equals(importo, that.importo) && logicalOperation == that.logicalOperation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataRicezione, destinatario, importo, logicalOperation);
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"destinatario\": {\"value\": \"pippo\",\"operation\": \"contains\"}}";
        FilterFatture filterFatture = objectMapper.readValue(json, FilterFatture.class);
        System.out.println(filterFatture);
    }

}
