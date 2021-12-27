package eapli.base.languagemanagement.domain.form;// Generated from FormValidation.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormValidationParser}.
 */
public interface FormValidationListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(FormValidationParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(FormValidationParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#ifVerification}.
	 * @param ctx the parse tree
	 */
	void enterIfVerification(FormValidationParser.IfVerificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#ifVerification}.
	 * @param ctx the parse tree
	 */
	void exitIfVerification(FormValidationParser.IfVerificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(FormValidationParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(FormValidationParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueSizeIs}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void enterValueSizeIs(FormValidationParser.ValueSizeIsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueSizeIs}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void exitValueSizeIs(FormValidationParser.ValueSizeIsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueIsBetween}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void enterValueIsBetween(FormValidationParser.ValueIsBetweenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueIsBetween}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void exitValueIsBetween(FormValidationParser.ValueIsBetweenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueIsMandatory}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void enterValueIsMandatory(FormValidationParser.ValueIsMandatoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueIsMandatory}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void exitValueIsMandatory(FormValidationParser.ValueIsMandatoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueVerifyLogicOrEqualConfig}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void enterValueVerifyLogicOrEqualConfig(FormValidationParser.ValueVerifyLogicOrEqualConfigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueVerifyLogicOrEqualConfig}
	 * labeled alternative in {@link FormValidationParser#config}.
	 * @param ctx the parse tree
	 */
	void exitValueVerifyLogicOrEqualConfig(FormValidationParser.ValueVerifyLogicOrEqualConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#contentconditions}.
	 * @param ctx the parse tree
	 */
	void enterContentconditions(FormValidationParser.ContentconditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#contentconditions}.
	 * @param ctx the parse tree
	 */
	void exitContentconditions(FormValidationParser.ContentconditionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionsNoOperator}
	 * labeled alternative in {@link FormValidationParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditionsNoOperator(FormValidationParser.ConditionsNoOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionsNoOperator}
	 * labeled alternative in {@link FormValidationParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditionsNoOperator(FormValidationParser.ConditionsNoOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionsOperator}
	 * labeled alternative in {@link FormValidationParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditionsOperator(FormValidationParser.ConditionsOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionsOperator}
	 * labeled alternative in {@link FormValidationParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditionsOperator(FormValidationParser.ConditionsOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueVerifyLogicOrEqual}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterValueVerifyLogicOrEqual(FormValidationParser.ValueVerifyLogicOrEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueVerifyLogicOrEqual}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitValueVerifyLogicOrEqual(FormValidationParser.ValueVerifyLogicOrEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueState}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterValueState(FormValidationParser.ValueStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueState}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitValueState(FormValidationParser.ValueStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueIsEqualString}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterValueIsEqualString(FormValidationParser.ValueIsEqualStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueIsEqualString}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitValueIsEqualString(FormValidationParser.ValueIsEqualStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueIsBoolean}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterValueIsBoolean(FormValidationParser.ValueIsBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueIsBoolean}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitValueIsBoolean(FormValidationParser.ValueIsBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanIsValue}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterBooleanIsValue(FormValidationParser.BooleanIsValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanIsValue}
	 * labeled alternative in {@link FormValidationParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitBooleanIsValue(FormValidationParser.BooleanIsValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#stringCondition}.
	 * @param ctx the parse tree
	 */
	void enterStringCondition(FormValidationParser.StringConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#stringCondition}.
	 * @param ctx the parse tree
	 */
	void exitStringCondition(FormValidationParser.StringConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#stringQuoteMarks}.
	 * @param ctx the parse tree
	 */
	void enterStringQuoteMarks(FormValidationParser.StringQuoteMarksContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#stringQuoteMarks}.
	 * @param ctx the parse tree
	 */
	void exitStringQuoteMarks(FormValidationParser.StringQuoteMarksContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(FormValidationParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(FormValidationParser.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#intExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntExpression(FormValidationParser.IntExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#intExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntExpression(FormValidationParser.IntExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#intValue}.
	 * @param ctx the parse tree
	 */
	void enterIntValue(FormValidationParser.IntValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#intValue}.
	 * @param ctx the parse tree
	 */
	void exitIntValue(FormValidationParser.IntValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#gap}.
	 * @param ctx the parse tree
	 */
	void enterGap(FormValidationParser.GapContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#gap}.
	 * @param ctx the parse tree
	 */
	void exitGap(FormValidationParser.GapContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(FormValidationParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(FormValidationParser.DeclarationContext ctx);
}