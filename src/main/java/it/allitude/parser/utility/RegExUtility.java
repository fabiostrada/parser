package it.allitude.parser.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtility {

    public static void main(String[] args) {
        // Espressione regolare  \((.*?)\)( and | or )?
        final String regex = "\\((.*?)\\)( and | or )?";
        final String string = "(destinatario contains pippo) or (importo gt 3000 and import lt 5000) or (dataRicezione gt 2021/01/01 and dataRicezione lte 2021/12/31)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {

            System.out.println("Full match: " + matcher.group(0));

            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }

}