import org.antlr.v4.runtime.tree.ParseTreeProperty;

import javax.xml.crypto.Data;

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
        types.put(ctx, DataType.INT);
        return DataType.INT;
    }

    @Override
    public DataType visitExBoolLiteral(OurLanguageParser.ExBoolLiteralContext ctx) {
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

        symbols.put(ctx, symbol);
        types.put(ctx, symbol.getType());
        return symbol.getType();
    }

    @Override
    public DataType visitExParentheses(OurLanguageParser.ExParenthesesContext ctx) {
        DataType exprType = visit(ctx.expression());

        types.put(ctx, exprType);

        System.out.println(exprType);

        return exprType;
    }

    @Override
    public DataType visitExAddOp(OurLanguageParser.ExAddOpContext ctx) {
        DataType leftType = visit( ctx.left);
        DataType rightType = visit( ctx.right);

        if( leftType != rightType) {
            throw new CompilerException( "Can only add similar values" );
        }

        types.put( ctx, leftType);
        return leftType;
    }

    @Override
    public DataType visitExMulOp(OurLanguageParser.ExMulOpContext ctx) {
        DataType leftType = visit( ctx.left);
        DataType rightType = visit( ctx.right);

        if( leftType != rightType) {
            throw new CompilerException( "Can only multiply similar values" );
        }

        types.put( ctx, leftType);
        return leftType;
    }

    /**
     * Statements
     */
    @Override
    public DataType visitPrintStatement(OurLanguageParser.PrintStatementContext ctx) {
        return super.visitPrintStatement(ctx);
    }

    /**
     * IF Statement
     */
    @Override
    public DataType visitCondition(OurLanguageParser.ConditionContext ctx) {
        DataType leftType = visit( ctx.left);
        DataType rightType = visit( ctx.right);

        if(leftType != rightType) {
            throw new CompilerException("Can only compare similar values");
        }

        types.put(ctx, DataType.BOOL);
        return DataType.BOOL;
    }

    @Override
    public DataType visitIfStatement(OurLanguageParser.IfStatementContext ctx) {

        ctx.condition().forEach(c -> {
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

    /**
     * Declaration & Assignment
     */
    @Override
    public DataType visitDeclaration(OurLanguageParser.DeclarationContext ctx) {
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
    public DataType visitWhileLoop(OurLanguageParser.WhileLoopContext ctx) {
        return super.visitWhileLoop(ctx);
    }
}
