import org.antlr.v4.runtime.tree.ParseTreeProperty;

import javax.xml.crypto.Data;

public class TypeChecker extends OurLanguageBaseVisitor<DataType> {
    private ParseTreeProperty<DataType> types = new ParseTreeProperty<>();
    private ParseTreeProperty<Symbol> symbols = new ParseTreeProperty<>();
    private Scope scope = new Scope(null, "GlobalScope");

    public ParseTreeProperty<DataType> getTypes() { return types; }
    public ParseTreeProperty<Symbol> getSymbols() { return symbols; }


//    public TypeChecker() {
//        Symbol symbol = new Symbol("args", DataType.STRING);
//        scope.addVariable(symbol);
//        scope.assignIndex(symbol);
//    }

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
        Symbol symbol = symbols.get(ctx);

        if(symbol == null)
            throw new CompilerException("Unknown variable");

        types.put(ctx, DataType.STRING);
        return DataType.STRING;
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

    @Override
    public DataType visitPrintStatement(OurLanguageParser.PrintStatementContext ctx) {
        DataType exprType = visit(ctx.expression());

        if( exprType == DataType.INT) {
            types.put( ctx, exprType);
        }
        if( exprType == DataType.STRING) {
            types.put( ctx, exprType);
        }
        return null;
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
        DataType conditionType = visit(ctx.condition());

        if(conditionType != DataType.BOOL) {
            throw new CompilerException("Condition is not a boolean");
        }
        return null;
    }

    @Override
    public DataType visitBlock(OurLanguageParser.BlockContext ctx) {
        scope = scope.createChild("Block");
//        for (OurLanguageParser.StatementContext statement : ctx.statement()) {
//            visit(statement);
//        }
        scope = scope.getParentScope();
        return null;
    }
}
