package it.allitude.parser.utility;

import it.allitude.parser.model.operation.LogicalOperation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtility {

    public static String REGEX_GROUP = "\\((.*?)\\)( and | or )?";

    public static Matcher matcherOf(String filterValue, String regex) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        return pattern.matcher(filterValue);
    }

    public static String regexGroup() {
        return REGEX_GROUP
                .replace("and", LogicalOperation.and.getValue())
                .replace("or", LogicalOperation.or.getValue());
    }
}


    /*
    public static void main(String[] args) {
        // Espressione regolare  \((.*?)\)( and | or )?

        final String regex = "\\((.*?)\\)( and | or )?";
        final String string = "(destinatario contains pippo) or (importo gt 3000 and import lt 5000) or (dataRicezione gt 2021/01/01 and dataRicezione lte 2021/12/31)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        matcher.results().forEach(result -> {
            System.out.println(result.group(0));
            System.out.println(result.group(1));
            System.out.println(result.group(2));
            System.out.println("---------------");
        });

        while (matcher.find()) {

            System.out.println("Full match: " + matcher.group(0));

            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }*/
