package eapli.base.languagemanagement.domain.task;// Generated from AutomaticTaskLanguage.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AutomaticTaskLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AutomaticTaskLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskLanguageParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(AutomaticTaskLanguageParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code searchfile}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearchfile(AutomaticTaskLanguageParser.SearchfileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code calculationArithmetic}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#calculation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculationArithmetic(AutomaticTaskLanguageParser.CalculationArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by the {@code number}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(AutomaticTaskLanguageParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(AutomaticTaskLanguageParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textinfoArithmetic}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextinfoArithmetic(AutomaticTaskLanguageParser.TextinfoArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationOrDivision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationOrDivision(AutomaticTaskLanguageParser.MultiplicationOrDivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additionOrSubtraction}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionOrSubtraction(AutomaticTaskLanguageParser.AdditionOrSubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code power}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(AutomaticTaskLanguageParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sendEmail}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#email}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSendEmail(AutomaticTaskLanguageParser.SendEmailContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filetextText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFiletextText(AutomaticTaskLanguageParser.FiletextTextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filetextDiv}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFiletextDiv(AutomaticTaskLanguageParser.FiletextDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filetextFileTextText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFiletextFileTextText(AutomaticTaskLanguageParser.FiletextFileTextTextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filetextFormValidation}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFiletextFormValidation(AutomaticTaskLanguageParser.FiletextFormValidationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emailTextWithTextInfo}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#emailtext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmailTextWithTextInfo(AutomaticTaskLanguageParser.EmailTextWithTextInfoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emailTextTextInfo}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#emailtext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmailTextTextInfo(AutomaticTaskLanguageParser.EmailTextTextInfoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textInfoFormResponse}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextInfoFormResponse(AutomaticTaskLanguageParser.TextInfoFormResponseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textInfoFileSearch}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextInfoFileSearch(AutomaticTaskLanguageParser.TextInfoFileSearchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textInfoCalculationSearch}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextInfoCalculationSearch(AutomaticTaskLanguageParser.TextInfoCalculationSearchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textInfoText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextInfoText(AutomaticTaskLanguageParser.TextInfoTextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textDecision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextDecision(AutomaticTaskLanguageParser.TextDecisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextNumber(AutomaticTaskLanguageParser.TextNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textTextDecision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextTextDecision(AutomaticTaskLanguageParser.TextTextDecisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textTextWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextTextWord(AutomaticTaskLanguageParser.TextTextWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextWord(AutomaticTaskLanguageParser.TextWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textTextNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextTextNumber(AutomaticTaskLanguageParser.TextTextNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileXmlFileName}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileXmlFileName(AutomaticTaskLanguageParser.FileXmlFileNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileXmlWithoutSlash}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileXmlWithoutSlash(AutomaticTaskLanguageParser.FileXmlWithoutSlashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileXmlWithSlash}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileXmlWithSlash(AutomaticTaskLanguageParser.FileXmlWithSlashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileNameWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileNameWord(AutomaticTaskLanguageParser.FileNameWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileNameWithWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileNameWithWord(AutomaticTaskLanguageParser.FileNameWithWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileNameWithNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileNameWithNumber(AutomaticTaskLanguageParser.FileNameWithNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fileNameNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileNameNumber(AutomaticTaskLanguageParser.FileNameNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskLanguageParser#formResponse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormResponse(AutomaticTaskLanguageParser.FormResponseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskLanguageParser#fileSearch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileSearch(AutomaticTaskLanguageParser.FileSearchContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskLanguageParser#calculationSearch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculationSearch(AutomaticTaskLanguageParser.CalculationSearchContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskLanguageParser#request}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequest(AutomaticTaskLanguageParser.RequestContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskLanguageParser#approval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApproval(AutomaticTaskLanguageParser.ApprovalContext ctx);
}