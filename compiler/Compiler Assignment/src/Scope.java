import java.util.HashMap;

public class Scope {

    private  HashMap<String, Symbol> symbols;
    private Scope parentScope;

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        symbols = new HashMap<String, Symbol>();
    }

    public void declareVariable(Symbol symbol) {
//        symbols.put("");
    }

    public Symbol lookupVariable(String name) {
        Symbol symbol = null;
        return symbol;
    }

    public Scope openScope() {
        Scope scope = null;
        return scope;
    }

    public Scope closeScope() {
        Scope scope = null;
        return scope;
    }
}
