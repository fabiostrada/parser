package it.allitude.parser.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.allitude.parser.model.impl.FilterFatture;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static it.allitude.parser.utility.JsonParser.contentJsonOfAllConditions;
import static it.allitude.parser.utility.JsonParser.contentJsonOfLogicalOperationOfGroup;
import static it.allitude.parser.utility.RegExUtility.REGEX_GROUP;
import static it.allitude.parser.utility.RegExUtility.matcherOf;
import static it.allitude.parser.utility.StringUtil.isNotEmpty;
import static it.allitude.parser.utility.StringUtil.isNullOrEmpty;

public class Parser<T> {

    private String filter;
    private static ObjectMapper mapper = new ObjectMapper();

    public Parser(String filter) {
        if (isNullOrEmpty(filter)) {
            throw new RuntimeException("Filter is null");
        }
        this.filter = filter;
    }

    public T build() {
        List<String> groupsOfConditions = groupsOfConditionsFrom();
        String logicalOperationOfGroups = logicalOperationOfGroupsFrom();
        String contentJsonOfAllConditions = contentJsonOfAllConditions(groupsOfConditions);
        String contentJsonOfLogicalOperationOfGroup = contentJsonOfLogicalOperationOfGroup(logicalOperationOfGroups);
        String finalJson = "{" + contentJsonOfAllConditions + "," + contentJsonOfLogicalOperationOfGroup  + "}";
        return null;
    }

    private List<String> groupsOfConditionsFrom() {
        Matcher matcherOfRegexGroup = matcherOf(filter, REGEX_GROUP);
        return matcherOfRegexGroup.results().map(this::groupOf).collect(Collectors.toList());
    }

    private String logicalOperationOfGroupsFrom() {
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

    public static void main(String[] args) {
        String filter = "(destinatario contains pippo) or (importo gt 3000 and import lt 5000) or (dataRicezione gt 2021/01/01 and dataRicezione lte 2021/12/31)";
        FilterFatture build = new Parser<FilterFatture>(filter).build();
    }
}
