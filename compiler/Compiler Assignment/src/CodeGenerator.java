import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;

/**
 * Code Generator for OurLanguage.
 */

public class CodeGenerator extends OurLanguageBaseVisitor< ArrayList<String> > {
    private ParseTreeProperty<DataType> types;
    private ParseTreeProperty<Symbol> symbols;

    public CodeGenerator( ParseTreeProperty<DataType> types, ParseTreeProperty<Symbol> symbols) {
        this.types = types;
        this.symbols = symbols;
    }

    @Override
    public ArrayList<String> visitStart(OurLanguageParser.StartContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        for (OurLanguageParser.StatementContext statement : ctx.statement() )
            code.addAll(visit(statement));
        code.add("return");
        return code;
    }


    /**
     * Expressions
     */
    @Override
    public ArrayList<String> visitExIntLiteral(OurLanguageParser.ExIntLiteralContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.add("ldc " + ctx.INT().getText());
        return code;
    }

    @Override
    public ArrayList<String> visitExStringLiteral(OurLanguageParser.ExStringLiteralContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.add("ldc " + ctx.STRING().getText());
        return code;
    }

    @Override
    public ArrayList<String> visitExIdentifier(OurLanguageParser.ExIdentifierContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.add("; Should do iload or aload here....." );
        return code;
    }

    @Override
    public ArrayList<String> visitExNegate(OurLanguageParser.ExNegateContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.add("ldc 0");
        code.add("ldc " + ctx.expression().getText());
        code.add("isub");
        return code;
    }

    @Override
    public ArrayList<String> visitExBoolLiteral(OurLanguageParser.ExBoolLiteralContext ctx) {
        return super.visitExBoolLiteral(ctx);
    }

    @Override
    public ArrayList<String> visitExAddOp(OurLanguageParser.ExAddOpContext ctx) {
        ArrayList<String> code = visit(ctx.left);
        code.addAll(visit(ctx.right));

        if (ctx.op.getText().equals("+"))
            code.add("iadd");
        else if (ctx.op.getText().equals("-"))
            code.add("isub");
        else
            assert false;
        return code;
    }

    @Override
    public ArrayList<String> visitExMulOp(OurLanguageParser.ExMulOpContext ctx) {
        ArrayList<String> code = visit( ctx.left );
        code.addAll( visit(ctx.right) );
        code.add("imul");
        return code;
    }

    /**
     * Statements
     */

    @Override
    public ArrayList<String> visitDeclaration(OurLanguageParser.DeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        DataType type = DataType.INT;
        int index = 0;

//        if( ctx.INT == null)
//            type = DataType.STRING;

        Symbol symbol = new Symbol(name, type);
        symbols.put(ctx, symbol);

        return null;
    }

    @Override
    public ArrayList<String> visitPrintStatement(OurLanguageParser.PrintStatementContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.add("getstatic java/lang/System/out Ljava/io/PrintStream;");
        code.addAll( visit(ctx.expression()) );

        if( types.get(ctx.expression()) == DataType.INT )
            code.add("invokevirtual java/io/PrintStream/println(I)V");
        else if( types.get(ctx.expression()) == DataType.STRING )
            code.add("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        else
            code.add("; Oops... should have a println()-call here...");
        return code;
    }


    /**
     * IF statement
     */
    @Override
    public ArrayList<String> visitCondition(OurLanguageParser.ConditionContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.add("ldc " + ctx.left.getText());
        code.add("ldc " + ctx.right.getText());

//        ctx.comp.getText().equals('<')

        return code;
    }

    @Override
    public ArrayList<String> visitIfStatement(OurLanguageParser.IfStatementContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        if(ctx.condition().getText() == "true") {
            code.addAll(visit(ctx.block()));
        } else if (ctx.condition().getText() == "false") {
            // go to else part of the statement
        } else {
            code.add("; If statement condition isn't true or false");
        }

        return code;
    }

    @Override
    public ArrayList<String> visitBlock(OurLanguageParser.BlockContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.addAll(visit(ctx.statement()));
        return code;
    }
}
