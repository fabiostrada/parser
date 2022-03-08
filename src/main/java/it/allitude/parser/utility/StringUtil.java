package it.allitude.parser.utility;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;

public class StringUtil {

    public static Boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static List<String> split(String str, String separator) {
        if (isNullOrEmpty((str)) || isNullOrEmpty(separator)) {
            return EMPTY_LIST;
        }
        return asList(str.split(separator));
    }

    public static List<String> split(String str, String separator, Integer maxNumberElement) {
        List<String> splitArray = split(str, separator);
        if (splitArray.size() <= maxNumberElement) {
            return splitArray;
        }
        List<String> startPartialResult = splitArray.subList(0, maxNumberElement - 1);
        String finalString = splitArray.subList(maxNumberElement - 1, splitArray.size())
                .stream().collect(Collectors.joining(separator));
        return Stream.concat(startPartialResult.stream(), Stream.of(finalString))
                     .collect(Collectors.toList());
    }

}
