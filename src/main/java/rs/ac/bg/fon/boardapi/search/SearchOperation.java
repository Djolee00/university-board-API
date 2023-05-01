package rs.ac.bg.fon.boardapi.search;

public enum SearchOperation {

    CONTAINS, LESS_THAN_EQUAL, GREATER_THAN_EQUAL, EQUAL,
    ALL,ANY;

    public static final String[] SIMPLE_OPERATION_SET={
            "cn","le","ge","eq"
    };

    public static SearchOperation getDataOption(final String dataOption){
        return switch (dataOption) {
            case "all" -> ALL;
            case "any" -> ANY;
            default -> null;
        };
    }

    public static SearchOperation getSimpleOperation(final String input){
        return switch (input) {
            case "cn" -> CONTAINS;
            case "le" -> LESS_THAN_EQUAL;
            case "ge" -> GREATER_THAN_EQUAL;
            case "eq" -> EQUAL;
            default -> null;
        };
    }
}
