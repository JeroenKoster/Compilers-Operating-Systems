import java.util.ArrayList;
import java.util.HashMap;

public class Scope {

    private Scope parentScope;
    private HashMap<String, Symbol> variables = new HashMap<>();
    private ArrayList<Scope> childScopes = new ArrayList<>();

    private String name;
    private int index;

    public Scope(Scope parentScope, String name) {
        this.parentScope = parentScope;
        this.name = name;

        if (parentScope == null) {
            this.index = 0;
        } else {
            this.index = parentScope.index;
        }
    }

    /**
     * Scope
     */
    public Scope createChild(String name) {
        return new Scope(this, name);
    }

    public Scope getParentScope() {
        return parentScope;
    }

    public Scope getGlobalParent() {
        // Could be implemented later
        if (parentScope != null) {
            return parentScope.getGlobalParent();
        }
        return this;
    }

    /**
     * Declaration
     */
    public Symbol declareVariable(String name, DataType type) {
        int index = variables.size();

        if (findVariable(name) == null){
            Symbol variableSymbol = new Symbol(name, type, index);
            variables.put(name, variableSymbol);
            return variableSymbol;
        } else {
//            throw new CompilerException("Variable " + name + " already exists");
            return null;
        }
    }

    public Symbol findVariable(String name) {
        if (parentScope != null) {
            if (variables.get(name) != null) {
                return variables.get(name);
            } else {
                return parentScope.findVariable(name);
            }
        } else {
            return variables.get(name);
        }
    }
}