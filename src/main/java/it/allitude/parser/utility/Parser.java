package it.allitude.parser.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser<T> {

    private String filter;
    private Class<T> clazz;
    private static ObjectMapper mapper = new ObjectMapper();

    public Parser(String filter, Class<T> clazz) {
        this.filter = filter;
        this.clazz = clazz;
    }

    public T build() {
        return null;
    }

}
