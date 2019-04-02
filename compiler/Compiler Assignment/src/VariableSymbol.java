public class VariableSymbol extends Symbol {

    private String text;
    private DataType type;
    private int index;

    public VariableSymbol(String text, DataType type, int index) {
        super(text, type, index);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
