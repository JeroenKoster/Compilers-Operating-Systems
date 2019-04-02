import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class TypeChecker extends OurLanguageBaseVisitor<DataType> {
    private ParseTreeProperty<DataType> types = new ParseTreeProperty<>();
    private ParseTreeProperty<Symbol> symbols = new ParseTreeProperty<>();
    private Scope scope = new Scope(null, null);

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
    public DataType visitExStringLiteral(OurLanguageParser.ExStringLiteralContext ctx) {
        types.put(ctx, DataType.STRING);
        return DataType.STRING;
    }

    @Override
    public DataType visitExIdentifier(OurLanguageParser.ExIdentifierContext ctx) {
//        String name = ctx.IDENTIFIER().getText();
//        VariableSymbol symbol = (VariableSymbol)symbols.get(name);
//
//        if(symbol == null)
//            throw new CompilerException("Unknown variable");

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
		String name = ctx.expression().getText();

        if( exprType == DataType.INT) {
            System.out.println("Printstatement expression datatype is int");
            types.put( ctx, exprType);
        }
        if( exprType == DataType.STRING) {
            System.out.println("Printstatement expression datatype is string");
            types.put( ctx, exprType);
        }
        System.out.println(types.toString());
        return null;
    }
}
