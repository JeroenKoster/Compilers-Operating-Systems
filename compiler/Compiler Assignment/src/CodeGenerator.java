import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;

/**
 * Code Generator for OurLanguage.
 */

public class CodeGenerator extends OurLanguageBaseVisitor< ArrayList<String> > {
    private ParseTreeProperty<DataType> types;
    private ParseTreeProperty<Symbol> symbols;
    private int LABELCOUNTER = 0;

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
        Symbol symbol = symbols.get(ctx);

        if (symbol.getType() == DataType.INT) {
            code.add("iload " + symbol.getIndex());
        } else if (symbol.getType() == DataType.STRING) {
            code.add("aload " + symbol.getIndex());
        } else if (symbol.getType() == DataType.BOOL) {
            code.add("iload " + symbol.getIndex());
        } else {
            throw new CompilerException("Can not load variable " + symbol.getName());
        }

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
        ArrayList<String> code = new ArrayList<>();

        if (ctx.BOOLEAN().getText().equals("true")) {
//            throw new CompilerException("THIS IS ME TRUE " + ctx.BOOLEAN().getText());
            code.add("ldc 1");
        } else if (ctx.BOOLEAN().getText().equals("false")) {
//            throw new CompilerException("THIS IS ME TRUE " + ctx.BOOLEAN().getText());
            code.add("ldc 0");
        } else {
            throw new CompilerException("Invalid boolean");
        }

        return code;
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
        ArrayList<String> code = new ArrayList<>();
        return code;
    }

    @Override
    public ArrayList<String> visitAssignment(OurLanguageParser.AssignmentContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        Symbol symbol = symbols.get(ctx);

        code.addAll(visit(ctx.expression()));

        if (symbol.getType() == DataType.INT) {
            code.add("istore " + symbol.getIndex());
        } else if (symbol.getType() == DataType.STRING) {
            code.add("astore " + symbol.getIndex());
        } else if (symbol.getType() == DataType.BOOL) {
            code.add("istore " + symbol.getIndex());
        } else {
            throw new CompilerException("Can not store variable " + ctx.IDENTIFIER().getText());
        }
        return code;
    }

    @Override
    public ArrayList<String> visitPrintStatement(OurLanguageParser.PrintStatementContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.add("getstatic java/lang/System/out Ljava/io/PrintStream;");
        code.addAll( visit(ctx.expression()) );

//        System.out.println(types.get(ctx.expression()));

        if( types.get(ctx.expression()) == DataType.INT)
            code.add("invokevirtual java/io/PrintStream/println(I)V");
        else if( types.get(ctx.expression()) == DataType.STRING )
            code.add("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        else if (types.get(ctx.expression()) == DataType.BOOL)
            code.add("invokevirtual java/io/PrintStream/println(Z)V");
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

        switch (ctx.comp.getText()) {
            case "<":
                code.add("if_icmplt true_" + LABELCOUNTER);
                break;
            case ">":
                code.add("if_icmpgt true_" + LABELCOUNTER);
                break;
            case "<=":
                code.add("if_icmple true_" + LABELCOUNTER);
                break;
            case ">=":
                code.add("if_icmpge true_" + LABELCOUNTER);
                break;
            case "==":
                code.add("if_icmpeq true_" + LABELCOUNTER);
                break;
            case "!=":
                code.add("if_icmpne true_" + LABELCOUNTER);
                break;
            default:
                throw new CompilerException("Error while comparing condition");
        }

        // if false returns ldc 0 and goto end
        code.add("\t ldc 0");
        code.add("\t goto end_" + LABELCOUNTER);
        // if true returns ldc 1
        code.add("true_" + LABELCOUNTER + ":");
        code.add("\t ldc 1");
        code.add("end_" + LABELCOUNTER + ":");

        LABELCOUNTER++;

        return code;
    }

    @Override
    public ArrayList<String> visitIfStatement(OurLanguageParser.IfStatementContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        // Loop through each of the if condition, skip to the corresponding block if its true
        for (int i = 0; i < ctx.condition().size(); i++) {
            code.addAll(visit(ctx.condition(i)));
            code.add("ldc 1");
            code.add("if_icmpeq true_1234_" + i);
            System.out.println("added true_" + LABELCOUNTER);
        }
        code.add("\t ldc 0");
        // If there are more blocks than conditions, an else block exists
        // There is probably a nicer way to do this check
        if (ctx.block().size() > ctx.condition().size()) {
            System.out.println("Else block detected");
            code.addAll(visit(ctx.block(ctx.block().size() - 1))); // If we reach this, none of the if- or elseIf-conditions was true
        }
        code.add("\t goto end_" + LABELCOUNTER); // Skip all the ifBlocks

        for (int i = 0; i < ctx.condition().size(); i++) { // Add blocks for each if / if-else statement
            code.add("true_1234_" + i + ":");
            code.add("\t ldc 1");
            code.addAll(visit(ctx.block(i)));
            code.add("\t goto end_" + LABELCOUNTER); // Skip all the remaining ifBlocks
        }
        code.add("end_" + LABELCOUNTER + ":");
        LABELCOUNTER++;

        return code;
    }

    @Override
    public ArrayList<String> visitBlock(OurLanguageParser.BlockContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        for (OurLanguageParser.StatementContext statement : ctx.statement() ) {
            code.addAll(visit(statement));
        }
        return code;
    }
}
