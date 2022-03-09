package it.allitude.parser.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.allitude.parser.model.impl.FilterFatture;

import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static it.allitude.parser.utility.JsonParser.contentJsonOfAllConditions;
import static it.allitude.parser.utility.JsonParser.contentJsonOfLogicalOperationOfGroup;
import static it.allitude.parser.utility.RegExUtility.REGEX_GROUP;
import static it.allitude.parser.utility.RegExUtility.matcherOf;
import static it.allitude.parser.utility.StringUtil.isNotEmpty;
import static it.allitude.parser.utility.StringUtil.isNullOrEmpty;

public class Parser {

    private String filter;
    private Class<?> clazz;
    private static ObjectMapper mapper = new ObjectMapper();

    public Parser(String filter, Class<?> clazz) {
        if (isNullOrEmpty(filter) && Objects.isNull(clazz)) {
            throw new RuntimeException("Filter or clazz is null");
        }
        this.filter = filter;
        this.clazz = clazz;
    }

    public<T> T build() throws JsonProcessingException {
        List<String> groupsOfConditions = groupsOfConditions();
        String logicalOperationOfGroups = logicalOperationOfGroups();
        String contentJsonOfAllConditions = contentJsonOfAllConditions(groupsOfConditions);
        String contentJsonOfLogicalOperationOfGroup = contentJsonOfLogicalOperationOfGroup(logicalOperationOfGroups);
        String finalJson = "{" + contentJsonOfAllConditions + "," + contentJsonOfLogicalOperationOfGroup  + "}";
        return (T)mapper.readValue(finalJson, clazz);
    }

    private List<String> groupsOfConditions() {
        Matcher matcherOfRegexGroup = matcherOf(filter, REGEX_GROUP);
        return matcherOfRegexGroup.results().map(this::groupOf).collect(Collectors.toList());
    }

    private String logicalOperationOfGroups() {
        Matcher matcherOfRegexGroup = matcherOf(filter, REGEX_GROUP);
        String logicalOperation = null;
        while(matcherOfRegexGroup.find()) {
            String currentLogicaOperationString = matcherOfRegexGroup.group(2);
            if (logicalOperationsOfGroupMustBeEquals(logicalOperation, currentLogicaOperationString)) {
                throw new RuntimeException("Error into parse process: logicalOperation of groups must be equal!");
            }
            if (isNotEmpty(currentLogicaOperationString)) {
                logicalOperation = currentLogicaOperationString;
            }
        }
        return logicalOperation;
    }

    private String groupOf(MatchResult result) {
        return result.group(1);
    }

    private boolean logicalOperationsOfGroupMustBeEquals(String logicalOperation, String currentLogicaOperationString) {
        return isNotEmpty(currentLogicaOperationString) && isNotEmpty(logicalOperation) && !currentLogicaOperationString.equals(logicalOperation);
    }

    public static void main(String[] args) throws JsonProcessingException {
        String filter = "(destinatario contains pippo) or (importo gt 3000 and import lt 5000) or (dataRicezione gt 2021/01/01 and dataRicezione lte 2021/12/31)";
        FilterFatture build = new Parser(filter, FilterFatture.class).build();
    }
}
