import org.antlr.v4.runtime.tree.ParseTreeProperty;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class TypeChecker extends OurLanguageBaseVisitor<DataType> {
    private ParseTreeProperty<DataType> types = new ParseTreeProperty<>();
    private ParseTreeProperty<Symbol> symbols = new ParseTreeProperty<>();
    private Scope scope = new Scope(null, "GlobalScope");

    public ParseTreeProperty<DataType> getTypes() { return types; }
    public ParseTreeProperty<Symbol> getSymbols() { return symbols; }

    /**
     * Expressions
     */
    @Override
    public DataType visitExIntLiteral(OurLanguageParser.ExIntLiteralContext ctx) {
        types.put(ctx, DataType.INT);
        return DataType.INT;
    }

    @Override
    public DataType visitExNegate(OurLanguageParser.ExNegateContext ctx) {
        if (visit(ctx.expression()) != DataType.INT) {
            throw new CompilerException("Invalid INT value");
        }
        types.put(ctx, DataType.INT);
        return DataType.INT;
    }

    @Override
    public DataType visitExBoolLiteral(OurLanguageParser.ExBoolLiteralContext ctx) {
        if( !ctx.BOOLEAN().getText().equals("true") && !ctx.BOOLEAN().getText().equals("false")){
            throw new CompilerException("Invalid BOOL value");
        }
        types.put(ctx, DataType.BOOL);
        return DataType.BOOL;
    }

    @Override
    public DataType visitExStringLiteral(OurLanguageParser.ExStringLiteralContext ctx) {
        types.put(ctx, DataType.STRING);
        return DataType.STRING;
    }

    @Override
    public DataType visitExIdentifier(OurLanguageParser.ExIdentifierContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Symbol symbol = scope.findVariable(name);

        if(symbol == null) {
            throw new CompilerException("Unknown variable");
        }
        if((symbol.getType() != DataType.INT) && (symbol.getType() != DataType.STRING) && (symbol.getType() != DataType.BOOL)) {
            throw new CompilerException("Unknown datatype " + symbol.getType());
        }

        symbols.put(ctx, symbol);
        types.put(ctx, symbol.getType());
        return symbol.getType();
    }

    @Override
    public DataType visitExParentheses(OurLanguageParser.ExParenthesesContext ctx) {
        DataType exprType = visit(ctx.expression());

        types.put(ctx, exprType);

        return exprType;
    }

    @Override
    public DataType visitExAddOp(OurLanguageParser.ExAddOpContext ctx) {
        DataType leftType = visit( ctx.left);
        DataType rightType = visit( ctx.right);

        if( leftType != rightType) {
            throw new CompilerException("Can only add or subtract values of INT");
        }
        if( (leftType != DataType.INT) || (rightType != DataType.INT)) {
            throw new CompilerException("Expressions not of datatype INT");
        }

        types.put( ctx, leftType);
        return leftType;
    }

    @Override
    public DataType visitExMulOp(OurLanguageParser.ExMulOpContext ctx) {
        DataType leftType = visit( ctx.left);
        DataType rightType = visit( ctx.right);
        String op = ctx.op.getText();

        if( leftType != rightType) {
            throw new CompilerException("Can only multiply or divide values of INT");
        }
        if( (leftType != DataType.INT) || (rightType != DataType.INT)) {
            throw new CompilerException("Expressions not of datatype INT");
        }

        if ((op != "*") && (op != "/")) {
            throw new CompilerException("Unknown comparison operator");
        }

        types.put( ctx, leftType);
        return leftType;
    }

    @Override
    public DataType visitLogicalCond(OurLanguageParser.LogicalCondContext ctx) {
        DataType leftType = visit( ctx.left);
        DataType rightType = visit( ctx.right);
        String comp = ctx.comp.getText();

        if( leftType != rightType) {
            throw new CompilerException("Can only compare values of BOOL");
        }
        if( (leftType != DataType.BOOL) || (rightType != DataType.BOOL)) {
            throw new CompilerException("Expressions not of datatype BOOL");
        }

        if ((comp != "<") && (comp != ">") && (comp != "<=") && (comp != ">=") && (comp != "==") && (comp != "!=")) {
            throw new CompilerException("Unknown comparison operator");
        }

        types.put(ctx, DataType.BOOL);
        return DataType.BOOL;
    }

    @Override
    public DataType visitLogicalOrCond(OurLanguageParser.LogicalOrCondContext ctx) {
        DataType leftType = visit(ctx.left);
        DataType rightType = visit(ctx.right);

        if( leftType != rightType) {
            throw new CompilerException("Can only compare values of BOOL");
        }
        if( (leftType != DataType.BOOL) || (rightType != DataType.BOOL)) {
            throw new CompilerException("Expressions not of datatype BOOL");
        }

        types.put(ctx, leftType);
        return leftType;
    }

    @Override
    public DataType visitLogicalAndCond(OurLanguageParser.LogicalAndCondContext ctx) {
        DataType leftType = visit(ctx.left);
        DataType rightType = visit(ctx.right);

        if( leftType != rightType) {
            throw new CompilerException("Can only compare values of BOOL");
        }
        if( (leftType != DataType.BOOL) || (rightType != DataType.BOOL)) {
            throw new CompilerException("Expressions not of datatype BOOL");
        }

        types.put(ctx, leftType);
        return leftType;
    }

    @Override
    public DataType visitLogicalNotCond(OurLanguageParser.LogicalNotCondContext ctx) {
        DataType conditionType = visit(ctx.expression());
        if(conditionType != DataType.BOOL) {
            throw new CompilerException("Condition is not a boolean value");
        }

        types.put(ctx, conditionType);
        return conditionType;
    }

    /**
     * Statements
     * */

    @Override
    public DataType visitDeclOnly(OurLanguageParser.DeclOnlyContext ctx) {
        DataType symbolType = null;

        if (ctx.variableName().getText().equals("INT")) {
            symbolType = DataType.INT;
        } else if (ctx.variableName().getText().equals("STRING")) {
            symbolType = DataType.STRING;
        } else if (ctx.variableName().getText().equals("BOOL")) {
            symbolType = DataType.BOOL;
        } else {
            throw new CompilerException("Invalid declaration type");
        }

        Symbol symbol = scope.declareVariable(ctx.IDENTIFIER().getText(), symbolType);
        symbols.put(ctx, symbol);

        return null;
    }

    @Override
    public DataType visitDeclAndAssignment(OurLanguageParser.DeclAndAssignmentContext ctx) {
        DataType symbolType = null;

        if (ctx.variableName().getText().equals("INT")) {
            symbolType = DataType.INT;
        } else if (ctx.variableName().getText().equals("STRING")) {
            symbolType = DataType.STRING;
        } else if (ctx.variableName().getText().equals("BOOL")) {
            symbolType = DataType.BOOL;
        } else {
            throw new CompilerException("Invalid declaration type");
        }

        Symbol symbol = scope.declareVariable(ctx.IDENTIFIER().getText(), symbolType);
        symbols.put(ctx, symbol);

        DataType expressionType = visit(ctx.expression()); // ldc 0 of ldc 1

        if (symbol != null) {
            if (symbolType != expressionType) {
                throw new CompilerException("Can not assign value to variable that has not the same type" + symbolType + " " + expressionType );
            }
        } else {
            throw new CompilerException("Can not assign value to " + symbolType );
        }

        symbols.put(ctx, symbol);

        return null;
    }

    @Override
    public DataType visitAssignment(OurLanguageParser.AssignmentContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        Symbol symbol = scope.findVariable(variableName);

        DataType variableType = symbol.getType();
        DataType expressionType = visit(ctx.expression()); // ldc 0 of ldc 1

        if (symbol != null) {
            if (variableType != expressionType) {
                throw new CompilerException("Can not assign value to variable that has not the same type" + variableType + " " + expressionType );
            }
        } else {
            throw new CompilerException("Can not assign value to " + variableName );
        }

        symbols.put(ctx, symbol);

        return null;
    }

    @Override
    public DataType visitIfStatement(OurLanguageParser.IfStatementContext ctx) {

        ctx.expression().forEach(c -> {
            DataType conditionType = visit(c);
            if(conditionType != DataType.BOOL) {
                throw new CompilerException("Condition is not a boolean");
            }
        });

        ctx.block().forEach(b -> {
            visit(b);
        });

        return null;
    }

    @Override
    public DataType visitBlock(OurLanguageParser.BlockContext ctx) {
        scope = scope.createChild("Block");
        for (OurLanguageParser.StatementContext statement : ctx.statement()) {
            visit(statement);
        }
        scope = scope.getParentScope();

        return null;
    }

    @Override
    public DataType visitWhileLoop(OurLanguageParser.WhileLoopContext ctx) {
        scope = scope.createChild("While");
        visit(ctx.block());
        scope = scope.getParentScope();
        return null;
    }
}
