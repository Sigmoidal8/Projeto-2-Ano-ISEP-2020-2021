package eapli.base.languagemanagement.domain.form;// Generated from FormValidation.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormValidationParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormValidationVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(FormValidationParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#ifVerification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfVerification(FormValidationParser.IfVerificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(FormValidationParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueSizeIs}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueSizeIs(FormValidationParser.ValueSizeIsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueIsBetween}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueIsBetween(FormValidationParser.ValueIsBetweenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueIsMandatory}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueIsMandatory(FormValidationParser.ValueIsMandatoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueVerifyLogicOrEqualConfig}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueVerifyLogicOrEqualConfig(FormValidationParser.ValueVerifyLogicOrEqualConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#contentconditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContentconditions(FormValidationParser.ContentconditionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conditionsNoOperator}
	 * labeled alternative in {@link FormValidationParser#conditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionsNoOperator(FormValidationParser.ConditionsNoOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conditionsOperator}
	 * labeled alternative in {@link FormValidationParser#conditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionsOperator(FormValidationParser.ConditionsOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueVerifyLogicOrEqual}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueVerifyLogicOrEqual(FormValidationParser.ValueVerifyLogicOrEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueState}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueState(FormValidationParser.ValueStateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueIsEqualString}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueIsEqualString(FormValidationParser.ValueIsEqualStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueIsBoolean}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueIsBoolean(FormValidationParser.ValueIsBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanIsValue}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanIsValue(FormValidationParser.BooleanIsValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#stringCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringCondition(FormValidationParser.StringConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#stringQuoteMarks}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringQuoteMarks(FormValidationParser.StringQuoteMarksContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#stringValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(FormValidationParser.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#intExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpression(FormValidationParser.IntExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#intValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntValue(FormValidationParser.IntValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#gap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGap(FormValidationParser.GapContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(FormValidationParser.DeclarationContext ctx);
}