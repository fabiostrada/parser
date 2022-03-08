package it.allitude.parser.utility;

import it.allitude.parser.utility.model.Filter;
import it.allitude.parser.utility.model.Operation;

import java.util.List;

import static it.allitude.parser.utility.StringUtil.isNullOrEmpty;
import static it.allitude.parser.utility.StringUtil.split;
import static java.util.stream.Collectors.toList;

public class Parser {

    public static String SEPARATOR = ";";
    public static String SEPARATOR_OF_CONDITION = " ";

    public static List<Filter> buildFrom(String filter) {
        List<String> conditions = split(filter, SEPARATOR);
        return conditions.stream()
                .map(Parser::build)
                .collect(toList());
    }

    public static Filter build(String condition) {
        isValidOrThrow(condition);
        List<String> splittedConditions = split(condition, SEPARATOR_OF_CONDITION, 3);
        return new Filter()
                .setParameter(splittedConditions.get(0))
                .setOperation(Operation.from(splittedConditions.get(1)))
                .setValue(splittedConditions.get(2));
    }

    private static void isValidOrThrow(String condition) {
        if (isNullOrEmpty(condition) || condition.split(SEPARATOR_OF_CONDITION).length < 3) {
            throw new RuntimeException("condition is not valid");
        }
    }

    public static void main(String[] args) {
        List<Filter> filters = Parser.buildFrom("parameter1 contains io mi chiamo joda;parameter2 = value2");
        System.out.println(filters);
    }

    /*
    PROBLEMA: Costruire un parametro filtro contenente condizioni in modo da poterle renderizzare dinamicamente in pojo
    SEMPLIFICAZIONI:
    1. La lista delle condizioni da inserire nel filtro devono essere o in AND o in OR. Non è prevista la casistica del tipo A and (B or C) opure A or (C and D)
    2. Di default sono in OR
    POSSIBILE SOLUZIONE
    - Tutte le condizioni sono separate da un carattere speciale. Preferibilmente il carattere ";"
    - Ciascuna condizione sarà caratterizzata da parametro operation value
    - la condizione min <= x <= max va spezzata in due sotto condizioni

    ESEMPI:
    - logicalOperation=OR&filter=parameter1 operation1 value1;parameter2 operation2 value2;
    - parameter <= 35;parameter >= 10;nome contains gianfilippo maria

    CRITICITA':
    E' prevista una casistica A OR min <=x <= max ?? Questa violerebbe il punto 1 delle semplificazioni
     */

}
