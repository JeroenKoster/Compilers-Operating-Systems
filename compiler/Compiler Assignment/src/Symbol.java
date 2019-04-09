public class Symbol {

    private String name;
    private DataType type;
    private int index;

    public Symbol(String name, DataType type, int index) {
        this.name = name;
        this.type = type;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
