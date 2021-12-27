package eapli.base.languagemanagement.domain.task;// Generated from AutomaticTaskLanguage.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AutomaticTaskLanguageParser}.
 */
public interface AutomaticTaskLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskLanguageParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(AutomaticTaskLanguageParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskLanguageParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(AutomaticTaskLanguageParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code searchfile}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#file}.
	 * @param ctx the parse tree
	 */
	void enterSearchfile(AutomaticTaskLanguageParser.SearchfileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code searchfile}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#file}.
	 * @param ctx the parse tree
	 */
	void exitSearchfile(AutomaticTaskLanguageParser.SearchfileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code calculationArithmetic}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#calculation}.
	 * @param ctx the parse tree
	 */
	void enterCalculationArithmetic(AutomaticTaskLanguageParser.CalculationArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by the {@code calculationArithmetic}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#calculation}.
	 * @param ctx the parse tree
	 */
	void exitCalculationArithmetic(AutomaticTaskLanguageParser.CalculationArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code number}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterNumber(AutomaticTaskLanguageParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code number}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitNumber(AutomaticTaskLanguageParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(AutomaticTaskLanguageParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(AutomaticTaskLanguageParser.ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textinfoArithmetic}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterTextinfoArithmetic(AutomaticTaskLanguageParser.TextinfoArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textinfoArithmetic}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitTextinfoArithmetic(AutomaticTaskLanguageParser.TextinfoArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationOrDivision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationOrDivision(AutomaticTaskLanguageParser.MultiplicationOrDivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationOrDivision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationOrDivision(AutomaticTaskLanguageParser.MultiplicationOrDivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additionOrSubtraction}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterAdditionOrSubtraction(AutomaticTaskLanguageParser.AdditionOrSubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additionOrSubtraction}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitAdditionOrSubtraction(AutomaticTaskLanguageParser.AdditionOrSubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code power}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterPower(AutomaticTaskLanguageParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code power}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitPower(AutomaticTaskLanguageParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sendEmail}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#email}.
	 * @param ctx the parse tree
	 */
	void enterSendEmail(AutomaticTaskLanguageParser.SendEmailContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sendEmail}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#email}.
	 * @param ctx the parse tree
	 */
	void exitSendEmail(AutomaticTaskLanguageParser.SendEmailContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filetextText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void enterFiletextText(AutomaticTaskLanguageParser.FiletextTextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filetextText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void exitFiletextText(AutomaticTaskLanguageParser.FiletextTextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filetextDiv}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void enterFiletextDiv(AutomaticTaskLanguageParser.FiletextDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filetextDiv}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void exitFiletextDiv(AutomaticTaskLanguageParser.FiletextDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filetextFileTextText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void enterFiletextFileTextText(AutomaticTaskLanguageParser.FiletextFileTextTextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filetextFileTextText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void exitFiletextFileTextText(AutomaticTaskLanguageParser.FiletextFileTextTextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filetextFormValidation}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void enterFiletextFormValidation(AutomaticTaskLanguageParser.FiletextFormValidationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filetextFormValidation}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filetext}.
	 * @param ctx the parse tree
	 */
	void exitFiletextFormValidation(AutomaticTaskLanguageParser.FiletextFormValidationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emailTextWithTextInfo}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#emailtext}.
	 * @param ctx the parse tree
	 */
	void enterEmailTextWithTextInfo(AutomaticTaskLanguageParser.EmailTextWithTextInfoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emailTextWithTextInfo}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#emailtext}.
	 * @param ctx the parse tree
	 */
	void exitEmailTextWithTextInfo(AutomaticTaskLanguageParser.EmailTextWithTextInfoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emailTextTextInfo}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#emailtext}.
	 * @param ctx the parse tree
	 */
	void enterEmailTextTextInfo(AutomaticTaskLanguageParser.EmailTextTextInfoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emailTextTextInfo}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#emailtext}.
	 * @param ctx the parse tree
	 */
	void exitEmailTextTextInfo(AutomaticTaskLanguageParser.EmailTextTextInfoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textInfoFormResponse}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void enterTextInfoFormResponse(AutomaticTaskLanguageParser.TextInfoFormResponseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textInfoFormResponse}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void exitTextInfoFormResponse(AutomaticTaskLanguageParser.TextInfoFormResponseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textInfoFileSearch}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void enterTextInfoFileSearch(AutomaticTaskLanguageParser.TextInfoFileSearchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textInfoFileSearch}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void exitTextInfoFileSearch(AutomaticTaskLanguageParser.TextInfoFileSearchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textInfoCalculationSearch}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void enterTextInfoCalculationSearch(AutomaticTaskLanguageParser.TextInfoCalculationSearchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textInfoCalculationSearch}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void exitTextInfoCalculationSearch(AutomaticTaskLanguageParser.TextInfoCalculationSearchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textInfoText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void enterTextInfoText(AutomaticTaskLanguageParser.TextInfoTextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textInfoText}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#textinfo}.
	 * @param ctx the parse tree
	 */
	void exitTextInfoText(AutomaticTaskLanguageParser.TextInfoTextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textDecision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void enterTextDecision(AutomaticTaskLanguageParser.TextDecisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textDecision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void exitTextDecision(AutomaticTaskLanguageParser.TextDecisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void enterTextNumber(AutomaticTaskLanguageParser.TextNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void exitTextNumber(AutomaticTaskLanguageParser.TextNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textTextDecision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void enterTextTextDecision(AutomaticTaskLanguageParser.TextTextDecisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textTextDecision}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void exitTextTextDecision(AutomaticTaskLanguageParser.TextTextDecisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textTextWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void enterTextTextWord(AutomaticTaskLanguageParser.TextTextWordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textTextWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void exitTextTextWord(AutomaticTaskLanguageParser.TextTextWordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void enterTextWord(AutomaticTaskLanguageParser.TextWordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void exitTextWord(AutomaticTaskLanguageParser.TextWordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textTextNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void enterTextTextNumber(AutomaticTaskLanguageParser.TextTextNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textTextNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#text}.
	 * @param ctx the parse tree
	 */
	void exitTextTextNumber(AutomaticTaskLanguageParser.TextTextNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileXmlFileName}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 */
	void enterFileXmlFileName(AutomaticTaskLanguageParser.FileXmlFileNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileXmlFileName}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 */
	void exitFileXmlFileName(AutomaticTaskLanguageParser.FileXmlFileNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileXmlWithoutSlash}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 */
	void enterFileXmlWithoutSlash(AutomaticTaskLanguageParser.FileXmlWithoutSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileXmlWithoutSlash}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 */
	void exitFileXmlWithoutSlash(AutomaticTaskLanguageParser.FileXmlWithoutSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileXmlWithSlash}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 */
	void enterFileXmlWithSlash(AutomaticTaskLanguageParser.FileXmlWithSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileXmlWithSlash}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#filexml}.
	 * @param ctx the parse tree
	 */
	void exitFileXmlWithSlash(AutomaticTaskLanguageParser.FileXmlWithSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileNameWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileNameWord(AutomaticTaskLanguageParser.FileNameWordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileNameWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileNameWord(AutomaticTaskLanguageParser.FileNameWordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileNameWithWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileNameWithWord(AutomaticTaskLanguageParser.FileNameWithWordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileNameWithWord}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileNameWithWord(AutomaticTaskLanguageParser.FileNameWithWordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileNameWithNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileNameWithNumber(AutomaticTaskLanguageParser.FileNameWithNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileNameWithNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileNameWithNumber(AutomaticTaskLanguageParser.FileNameWithNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fileNameNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileNameNumber(AutomaticTaskLanguageParser.FileNameNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fileNameNumber}
	 * labeled alternative in {@link AutomaticTaskLanguageParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileNameNumber(AutomaticTaskLanguageParser.FileNameNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskLanguageParser#formResponse}.
	 * @param ctx the parse tree
	 */
	void enterFormResponse(AutomaticTaskLanguageParser.FormResponseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskLanguageParser#formResponse}.
	 * @param ctx the parse tree
	 */
	void exitFormResponse(AutomaticTaskLanguageParser.FormResponseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskLanguageParser#fileSearch}.
	 * @param ctx the parse tree
	 */
	void enterFileSearch(AutomaticTaskLanguageParser.FileSearchContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskLanguageParser#fileSearch}.
	 * @param ctx the parse tree
	 */
	void exitFileSearch(AutomaticTaskLanguageParser.FileSearchContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskLanguageParser#calculationSearch}.
	 * @param ctx the parse tree
	 */
	void enterCalculationSearch(AutomaticTaskLanguageParser.CalculationSearchContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskLanguageParser#calculationSearch}.
	 * @param ctx the parse tree
	 */
	void exitCalculationSearch(AutomaticTaskLanguageParser.CalculationSearchContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskLanguageParser#request}.
	 * @param ctx the parse tree
	 */
	void enterRequest(AutomaticTaskLanguageParser.RequestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskLanguageParser#request}.
	 * @param ctx the parse tree
	 */
	void exitRequest(AutomaticTaskLanguageParser.RequestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskLanguageParser#approval}.
	 * @param ctx the parse tree
	 */
	void enterApproval(AutomaticTaskLanguageParser.ApprovalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskLanguageParser#approval}.
	 * @param ctx the parse tree
	 */
	void exitApproval(AutomaticTaskLanguageParser.ApprovalContext ctx);
}