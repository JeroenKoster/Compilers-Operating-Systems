import java.util.ArrayList;
import java.util.HashMap;

public class Scope {

    private Scope parentScope;
    private HashMap<String, Symbol> variables = new HashMap<>();
    private ArrayList<Scope> childScopes = new ArrayList<>();

    private String name;
    private int index;
//    private int stack;

    public Scope(Scope parentScope, String name) {
        this.parentScope = parentScope;
        this.name = name;

        if (parentScope == null) {
            this.index = 0;
            this.index = parentScope.index;
        }
    }

    public Scope createChild(String name) {
        return new Scope(this, name);
    }

    public boolean addChildScope(Scope scope) {
        return childScopes.add(scope);
    }

    public boolean addVariable(Symbol variable) {
        Symbol symbol = findVariable(variable.getName());
        if (symbol == null) {
            variables.put(variable.getName(), variable);
            return true;
        }
        return false;
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

    public Scope getGlobalParent() {
        if (parentScope != null) {
            return parentScope.getGlobalParent();
        }
        return this;
    }

    public void assignIndex(Symbol symbol) {
        symbol.setIndex(index++);
    }

    public String getName() {
        return name;
    }

    public Scope getParentScope() {
        return parentScope;
    }

    public ArrayList<Scope> getChildScopes() {
        return childScopes;
    }

    public void setName(String name) {
        this.name = name;
    }
}
