package it.allitude.parser.utility;

import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {

    public static String contentJsonOfAllConditions(List<String> groupsOfConditions) {
        return groupsOfConditions.stream().map(JsonParser::toJson).collect(Collectors.joining(","));
    }

    public static String contentJsonOfLogicalOperationOfGroup(String logicalOperationOfGroups) {
        return "\"logicalOperation\":\"" + logicalOperationOfGroups.trim() + "\"";
    }

    private static String toJson(String conditions) {
        return "   \"destinatario\":{\n" +
                "\t  \"value\":\"pippo\",\n" +
                "\t  \"operation\":\"contains\"\n" +
                "   }";
    }

}
