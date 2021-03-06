// Generated from C:/Users/nicky/Desktop/Compilers/Compilers-Operating-Systems/compiler/Compiler Assignment/src\OurLanguage.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OurLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OurLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(OurLanguageParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(OurLanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(OurLanguageParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#whileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(OurLanguageParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclOnly}
	 * labeled alternative in {@link OurLanguageParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclOnly(OurLanguageParser.DeclOnlyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclAndAssignment}
	 * labeled alternative in {@link OurLanguageParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclAndAssignment(OurLanguageParser.DeclAndAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#variableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableName(OurLanguageParser.VariableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(OurLanguageParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(OurLanguageParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurLanguageParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(OurLanguageParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOrCond}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrCond(OurLanguageParser.LogicalOrCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExNegate}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExNegate(OurLanguageParser.ExNegateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExIdentifier}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExIdentifier(OurLanguageParser.ExIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExMulOp}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExMulOp(OurLanguageParser.ExMulOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalNotCond}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalNotCond(OurLanguageParser.LogicalNotCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExAddOp}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExAddOp(OurLanguageParser.ExAddOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalCond}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalCond(OurLanguageParser.LogicalCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExBoolLiteral}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExBoolLiteral(OurLanguageParser.ExBoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExIntLiteral}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExIntLiteral(OurLanguageParser.ExIntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExParentheses}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExParentheses(OurLanguageParser.ExParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalAndCond}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndCond(OurLanguageParser.LogicalAndCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExStringLiteral}
	 * labeled alternative in {@link OurLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExStringLiteral(OurLanguageParser.ExStringLiteralContext ctx);
}