package rs.ac.bg.fon.boardapi.util;

public class SearchCriteria {

    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public SearchCriteria(String filterKey, Object value, String operation, String dataOption) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
        this.dataOption = dataOption;
    }

    public SearchCriteria(String filterKey, Object value, String operation) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
    }

    public SearchCriteria() {
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDataOption() {
        return dataOption;
    }

    public void setDataOption(String dataOption) {
        this.dataOption = dataOption;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "filterKey='" + filterKey + '\'' +
                ", value=" + value +
                ", operation='" + operation + '\'' +
                ", dataOption='" + dataOption + '\'' +
                '}';
    }
}
