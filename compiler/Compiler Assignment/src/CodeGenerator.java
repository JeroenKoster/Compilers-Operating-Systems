import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Code Generator for OurLanguage.
 */

public class CodeGenerator extends OurLanguageBaseVisitor< ArrayList<String> > {
    private ParseTreeProperty<DataType> types;
    private ParseTreeProperty<Symbol> symbols;
    private int LABELCOUNTER = 0;
    private int LOOPCOUNTER = 0;

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
        code.add("ldc " + ctx.expression().getText());
        code.add("ineg");
        return code;
    }

    @Override
    public ArrayList<String> visitExBoolLiteral(OurLanguageParser.ExBoolLiteralContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        if (ctx.BOOLEAN().getText().equals("true")) {
            code.add("ldc 1");
        } else if (ctx.BOOLEAN().getText().equals("false")) {
            code.add("ldc 0");
        } else {
            throw new CompilerException("Invalid boolean");
        }

        return code;
    }

    @Override
    public ArrayList<String> visitExParentheses(OurLanguageParser.ExParenthesesContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.addAll(visit(ctx.expression()));

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

        if(ctx.op.getText().equals("*")){
            code.add("imul");
        } else if(ctx.op.getText().equals("/")){
            code.add("idiv");
        }

        return code;
    }

    /**
     * Statements
     */

    @Override
    public ArrayList<String> visitDeclOnly(OurLanguageParser.DeclOnlyContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        return code;
    }

    @Override
    public ArrayList<String> visitDeclAndAssignment(OurLanguageParser.DeclAndAssignmentContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        Symbol symbol = symbols.get(ctx);

        code.addAll(visit(ctx.expression()));
        code.add(assignment(symbol));

        return code;
    }

    private String assignment(Symbol symbol) {
        String assingmentString;

        if (symbol.getType() == DataType.INT) {
            assingmentString = "istore " + symbol.getIndex();
        } else if (symbol.getType() == DataType.STRING) {
            assingmentString = "astore " + symbol.getIndex();
        } else if (symbol.getType() == DataType.BOOL) {
            assingmentString = "istore " + symbol.getIndex();
        } else {
            throw new CompilerException("Can not store variable " + symbol.getName());
        }

        return assingmentString;
    }

    @Override
    public ArrayList<String> visitAssignment(OurLanguageParser.AssignmentContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        Symbol symbol = symbols.get(ctx);

        code.addAll(visit(ctx.expression()));
        code.add(assignment(symbol));

        return code;
    }

    @Override
    public ArrayList<String> visitPrintStatement(OurLanguageParser.PrintStatementContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.add("getstatic java/lang/System/out Ljava/io/PrintStream;");
        code.addAll( visit(ctx.expression()) );

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
    public ArrayList<String> visitConditionalCond(OurLanguageParser.ConditionalCondContext ctx) {
        ArrayList<String> code = new ArrayList<>();
        code.addAll(visit(ctx.left));
        code.addAll(visit(ctx.right));

        switch (ctx.comp.getText()) {
            case "<":
                code.add("if_icmplt true_condition_" + LABELCOUNTER);
                break;
            case ">":
                code.add("if_icmpgt true_condition_" + LABELCOUNTER);
                break;
            case "<=":
                code.add("if_icmple true_condition_" + LABELCOUNTER);
                break;
            case ">=":
                code.add("if_icmpge true_condition_" + LABELCOUNTER);
                break;
            case "==":
                code.add("if_icmpeq true_condition_" + LABELCOUNTER);
                break;
            case "!=":
                code.add("if_icmpne true_condition_" + LABELCOUNTER);
                break;
            default:
                throw new CompilerException("Error while comparing condition");
        }

        // if false returns ldc 0 and goto end
        code.add("\t ldc 0");
        code.add("\t goto end_" + LABELCOUNTER);
        // if true returns ldc 1
        code.add("true_condition_" + LABELCOUNTER + ":");
        code.add("\t ldc 1");
        code.add("end_" + LABELCOUNTER + ":");

        LABELCOUNTER++;

        return code;
    }

    @Override
    public ArrayList<String> visitLogicalOrCond(OurLanguageParser.LogicalOrCondContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.addAll(visit(ctx.left));
        code.addAll(visit(ctx.right));
        code.add("ior");

        return code;
    }

    @Override
    public ArrayList<String> visitLogicalAndCond(OurLanguageParser.LogicalAndCondContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.addAll(visit(ctx.left));
        code.addAll(visit(ctx.right));
        code.add("iand");

        return code;
    }

    @Override
    public ArrayList<String> visitLogicalNotCond(OurLanguageParser.LogicalNotCondContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.addAll(visit(ctx.condition()));
        code.add("ldc 1");
        code.add("ixor");

//
//        System.out.println(visit(ctx.condition()));
//
//
//        if (visit(ctx.condition()).get(0).equals("ldc 0")) {
//            code.add("ldc 1");
//        } else if (visit(ctx.condition()).get(0).equals("ldc 1")){
//            code.add("ldc 0");
//        } else {
//            throw new CompilerException("Invalid NOT condition value");
//        }

        return code;
    }

    @Override
    public ArrayList<String> visitLogicalExBool(OurLanguageParser.LogicalExBoolContext ctx) {
        return super.visitLogicalExBool(ctx);
    }

    @Override
    public ArrayList<String> visitIfStatement(OurLanguageParser.IfStatementContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        // Loop through each of the if condition, skip to the corresponding block if its true
        for (int i = 0; i < ctx.condition().size(); i++) {
            code.addAll(visit(ctx.condition(i)));
            code.add("ldc 1");
            code.add("if_icmpeq true_if_" + i + "_" +  LABELCOUNTER);
            code.add("true_if_" + i + "_"+ LABELCOUNTER + ":");
        }
        code.add("\t ldc 0");
        // If there are more blocks than conditions, an else block exists
        // There is probably a nicer way to do this check
        if (ctx.block().size() > ctx.condition().size()) {
            code.addAll(visit(ctx.block(ctx.block().size() - 1))); // If we reach this, none of the if- or elseIf-conditions was true
        }
        code.add("\t goto end_" + LABELCOUNTER); // Skip all the ifBlocks

        for (int i = 0; i < ctx.condition().size(); i++) { // Add blocks for each if / if-else statement
            code.add("true_if_" + i + "_" + LABELCOUNTER + ":");
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

    @Override
    public ArrayList<String> visitWhileLoop(OurLanguageParser.WhileLoopContext ctx) {
        ArrayList<String> code = new ArrayList<>();

        code.add("while_"+ LOOPCOUNTER + ":");
        code.addAll(visit(ctx.condition()));
        code.add("ldc 1");
        code.add("if_icmpeq true_while_" + LOOPCOUNTER);
        code.add("\t goto end_while_" + LOOPCOUNTER);
        code.add("true_while_" + LOOPCOUNTER +":");
        code.addAll(visit(ctx.block()));
        code.add("goto while_" + LOOPCOUNTER);
        code.add("end_while_" + LOOPCOUNTER + ":");

        LOOPCOUNTER++;

        return code;
    }
}
