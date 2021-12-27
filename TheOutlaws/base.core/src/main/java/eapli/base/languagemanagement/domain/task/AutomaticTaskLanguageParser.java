package eapli.base.languagemanagement.domain.task;// Generated from AutomaticTaskLanguage.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AutomaticTaskLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, Decision=19, Email=20, Word=21, Number=22, POW=23, MUL=24, DIV=25, 
		ADD=26, SUB=27, XML=28, WS=29;
	public static final int
		RULE_prog = 0, RULE_file = 1, RULE_calculation = 2, RULE_arithmetic = 3, 
		RULE_email = 4, RULE_filetext = 5, RULE_emailtext = 6, RULE_textinfo = 7, 
		RULE_text = 8, RULE_filexml = 9, RULE_fileName = 10, RULE_formResponse = 11, 
		RULE_fileSearch = 12, RULE_calculationSearch = 13, RULE_request = 14, 
		RULE_approval = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "file", "calculation", "arithmetic", "email", "filetext", "emailtext", 
			"textinfo", "text", "filexml", "fileName", "formResponse", "fileSearch", 
			"calculationSearch", "request", "approval"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'search'", "'file'", "':'", "';'", "'calculation'", "'('", "')'", 
			"'send'", "'email'", "'|'", "'='", "'{'", "'File'", "'}'", "'Calculation'", 
			"'Form'", "'Request'", "'Approval'", "'{Decision}'", null, null, null, 
			"'^'", "'*'", "'/'", "'+'", "'-'", "'.xml'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "Decision", "Email", "Word", 
			"Number", "POW", "MUL", "DIV", "ADD", "SUB", "XML", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AutomaticTaskLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AutomaticTaskLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public EmailContext email() {
			return getRuleContext(EmailContext.class,0);
		}
		public FileContext file() {
			return getRuleContext(FileContext.class,0);
		}
		public CalculationContext calculation() {
			return getRuleContext(CalculationContext.class,0);
		}
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		return prog(0);
	}

	private ProgContext prog(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProgContext _localctx = new ProgContext(_ctx, _parentState);
		ProgContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_prog, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				{
				setState(33);
				email();
				}
				break;
			case T__0:
				{
				setState(34);
				file();
				}
				break;
			case T__4:
				{
				setState(35);
				calculation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(44);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ProgContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_prog);
						setState(38);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(39);
						email();
						}
						break;
					case 2:
						{
						_localctx = new ProgContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_prog);
						setState(40);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(41);
						file();
						}
						break;
					case 3:
						{
						_localctx = new ProgContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_prog);
						setState(42);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(43);
						calculation();
						}
						break;
					}
					} 
				}
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FileContext extends ParserRuleContext {
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
	 
		public FileContext() { }
		public void copyFrom(FileContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SearchfileContext extends FileContext {
		public FilexmlContext filexml() {
			return getRuleContext(FilexmlContext.class,0);
		}
		public List<FiletextContext> filetext() {
			return getRuleContexts(FiletextContext.class);
		}
		public FiletextContext filetext(int i) {
			return getRuleContext(FiletextContext.class,i);
		}
		public SearchfileContext(FileContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterSearchfile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitSearchfile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitSearchfile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_file);
		try {
			_localctx = new SearchfileContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(T__0);
			setState(50);
			match(T__1);
			setState(51);
			filexml(0);
			setState(52);
			match(T__2);
			setState(53);
			filetext(0);
			setState(54);
			match(T__2);
			setState(55);
			filetext(0);
			setState(56);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalculationContext extends ParserRuleContext {
		public CalculationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculation; }
	 
		public CalculationContext() { }
		public void copyFrom(CalculationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CalculationArithmeticContext extends CalculationContext {
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public CalculationArithmeticContext(CalculationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterCalculationArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitCalculationArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitCalculationArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculationContext calculation() throws RecognitionException {
		CalculationContext _localctx = new CalculationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_calculation);
		try {
			_localctx = new CalculationArithmeticContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__4);
			setState(59);
			match(T__2);
			setState(60);
			arithmetic(0);
			setState(61);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticContext extends ParserRuleContext {
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
	 
		public ArithmeticContext() { }
		public void copyFrom(ArithmeticContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NumberContext extends ArithmeticContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public NumberContext(ArithmeticContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesesContext extends ArithmeticContext {
		public ArithmeticContext inner;
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public ParenthesesContext(ArithmeticContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextinfoArithmeticContext extends ArithmeticContext {
		public TextinfoContext textinfo() {
			return getRuleContext(TextinfoContext.class,0);
		}
		public TextinfoArithmeticContext(ArithmeticContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextinfoArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextinfoArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextinfoArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicationOrDivisionContext extends ArithmeticContext {
		public ArithmeticContext left;
		public Token operator;
		public ArithmeticContext right;
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public TerminalNode MUL() { return getToken(AutomaticTaskLanguageParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(AutomaticTaskLanguageParser.DIV, 0); }
		public MultiplicationOrDivisionContext(ArithmeticContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterMultiplicationOrDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitMultiplicationOrDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitMultiplicationOrDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditionOrSubtractionContext extends ArithmeticContext {
		public ArithmeticContext left;
		public Token operator;
		public ArithmeticContext right;
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public TerminalNode ADD() { return getToken(AutomaticTaskLanguageParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(AutomaticTaskLanguageParser.SUB, 0); }
		public AdditionOrSubtractionContext(ArithmeticContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterAdditionOrSubtraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitAdditionOrSubtraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitAdditionOrSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PowerContext extends ArithmeticContext {
		public ArithmeticContext left;
		public Token operator;
		public ArithmeticContext right;
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public TerminalNode POW() { return getToken(AutomaticTaskLanguageParser.POW, 0); }
		public PowerContext(ArithmeticContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		return arithmetic(0);
	}

	private ArithmeticContext arithmetic(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, _parentState);
		ArithmeticContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_arithmetic, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new TextinfoArithmeticContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(64);
				textinfo();
				}
				break;
			case 2:
				{
				_localctx = new NumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				match(Number);
				}
				break;
			case 3:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(66);
				match(T__5);
				setState(67);
				((ParenthesesContext)_localctx).inner = arithmetic(0);
				setState(68);
				match(T__6);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(81);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new PowerContext(new ArithmeticContext(_parentctx, _parentState));
						((PowerContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(72);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(73);
						((PowerContext)_localctx).operator = match(POW);
						setState(74);
						((PowerContext)_localctx).right = arithmetic(4);
						}
						break;
					case 2:
						{
						_localctx = new MultiplicationOrDivisionContext(new ArithmeticContext(_parentctx, _parentState));
						((MultiplicationOrDivisionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(75);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(76);
						((MultiplicationOrDivisionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MultiplicationOrDivisionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(77);
						((MultiplicationOrDivisionContext)_localctx).right = arithmetic(3);
						}
						break;
					case 3:
						{
						_localctx = new AdditionOrSubtractionContext(new ArithmeticContext(_parentctx, _parentState));
						((AdditionOrSubtractionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(78);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(79);
						((AdditionOrSubtractionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AdditionOrSubtractionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(80);
						((AdditionOrSubtractionContext)_localctx).right = arithmetic(2);
						}
						break;
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EmailContext extends ParserRuleContext {
		public EmailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_email; }
	 
		public EmailContext() { }
		public void copyFrom(EmailContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SendEmailContext extends EmailContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public EmailtextContext emailtext() {
			return getRuleContext(EmailtextContext.class,0);
		}
		public SendEmailContext(EmailContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterSendEmail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitSendEmail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitSendEmail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmailContext email() throws RecognitionException {
		EmailContext _localctx = new EmailContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_email);
		try {
			_localctx = new SendEmailContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__7);
			setState(87);
			match(T__8);
			setState(88);
			match(T__9);
			setState(89);
			text(0);
			setState(90);
			match(T__9);
			setState(91);
			emailtext(0);
			setState(92);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FiletextContext extends ParserRuleContext {
		public FiletextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filetext; }
	 
		public FiletextContext() { }
		public void copyFrom(FiletextContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FiletextTextContext extends FiletextContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public FiletextTextContext(FiletextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFiletextText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFiletextText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFiletextText(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FiletextDivContext extends FiletextContext {
		public FiletextContext filetext() {
			return getRuleContext(FiletextContext.class,0);
		}
		public TerminalNode DIV() { return getToken(AutomaticTaskLanguageParser.DIV, 0); }
		public FiletextDivContext(FiletextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFiletextDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFiletextDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFiletextDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FiletextFileTextTextContext extends FiletextContext {
		public FiletextContext filetext() {
			return getRuleContext(FiletextContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public FiletextFileTextTextContext(FiletextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFiletextFileTextText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFiletextFileTextText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFiletextFileTextText(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FiletextFormValidationContext extends FiletextContext {
		public FiletextContext filetext() {
			return getRuleContext(FiletextContext.class,0);
		}
		public TerminalNode DIV() { return getToken(AutomaticTaskLanguageParser.DIV, 0); }
		public List<TextinfoContext> textinfo() {
			return getRuleContexts(TextinfoContext.class);
		}
		public TextinfoContext textinfo(int i) {
			return getRuleContext(TextinfoContext.class,i);
		}
		public FiletextFormValidationContext(FiletextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFiletextFormValidation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFiletextFormValidation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFiletextFormValidation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FiletextContext filetext() throws RecognitionException {
		return filetext(0);
	}

	private FiletextContext filetext(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FiletextContext _localctx = new FiletextContext(_ctx, _parentState);
		FiletextContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_filetext, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new FiletextTextContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(95);
			text(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(109);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(107);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new FiletextFileTextTextContext(new FiletextContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filetext);
						setState(97);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(98);
						text(0);
						}
						break;
					case 2:
						{
						_localctx = new FiletextDivContext(new FiletextContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filetext);
						setState(99);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(100);
						match(DIV);
						}
						break;
					case 3:
						{
						_localctx = new FiletextFormValidationContext(new FiletextContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filetext);
						setState(101);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(102);
						match(DIV);
						setState(103);
						textinfo();
						setState(104);
						match(T__10);
						setState(105);
						textinfo();
						}
						break;
					}
					} 
				}
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EmailtextContext extends ParserRuleContext {
		public EmailtextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emailtext; }
	 
		public EmailtextContext() { }
		public void copyFrom(EmailtextContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmailTextWithTextInfoContext extends EmailtextContext {
		public EmailtextContext emailtext() {
			return getRuleContext(EmailtextContext.class,0);
		}
		public TextinfoContext textinfo() {
			return getRuleContext(TextinfoContext.class,0);
		}
		public EmailTextWithTextInfoContext(EmailtextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterEmailTextWithTextInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitEmailTextWithTextInfo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitEmailTextWithTextInfo(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmailTextTextInfoContext extends EmailtextContext {
		public TextinfoContext textinfo() {
			return getRuleContext(TextinfoContext.class,0);
		}
		public EmailTextTextInfoContext(EmailtextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterEmailTextTextInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitEmailTextTextInfo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitEmailTextTextInfo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmailtextContext emailtext() throws RecognitionException {
		return emailtext(0);
	}

	private EmailtextContext emailtext(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EmailtextContext _localctx = new EmailtextContext(_ctx, _parentState);
		EmailtextContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_emailtext, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new EmailTextTextInfoContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(113);
			textinfo();
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new EmailTextWithTextInfoContext(new EmailtextContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_emailtext);
					setState(115);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(116);
					textinfo();
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TextinfoContext extends ParserRuleContext {
		public TextinfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textinfo; }
	 
		public TextinfoContext() { }
		public void copyFrom(TextinfoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TextInfoTextContext extends TextinfoContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public TextInfoTextContext(TextinfoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextInfoText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextInfoText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextInfoText(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextInfoFormResponseContext extends TextinfoContext {
		public FormResponseContext formResponse() {
			return getRuleContext(FormResponseContext.class,0);
		}
		public TextInfoFormResponseContext(TextinfoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextInfoFormResponse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextInfoFormResponse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextInfoFormResponse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextInfoFileSearchContext extends TextinfoContext {
		public FileSearchContext fileSearch() {
			return getRuleContext(FileSearchContext.class,0);
		}
		public TextInfoFileSearchContext(TextinfoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextInfoFileSearch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextInfoFileSearch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextInfoFileSearch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextInfoCalculationSearchContext extends TextinfoContext {
		public CalculationSearchContext calculationSearch() {
			return getRuleContext(CalculationSearchContext.class,0);
		}
		public TextInfoCalculationSearchContext(TextinfoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextInfoCalculationSearch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextInfoCalculationSearch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextInfoCalculationSearch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextinfoContext textinfo() throws RecognitionException {
		TextinfoContext _localctx = new TextinfoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_textinfo);
		try {
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new TextInfoFormResponseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				formResponse();
				}
				break;
			case 2:
				_localctx = new TextInfoFileSearchContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				fileSearch();
				}
				break;
			case 3:
				_localctx = new TextInfoCalculationSearchContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				calculationSearch();
				}
				break;
			case 4:
				_localctx = new TextInfoTextContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				text(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextContext extends ParserRuleContext {
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
	 
		public TextContext() { }
		public void copyFrom(TextContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TextDecisionContext extends TextContext {
		public TerminalNode Decision() { return getToken(AutomaticTaskLanguageParser.Decision, 0); }
		public TextDecisionContext(TextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextDecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextDecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextDecision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextNumberContext extends TextContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public TextNumberContext(TextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextTextDecisionContext extends TextContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public TerminalNode Decision() { return getToken(AutomaticTaskLanguageParser.Decision, 0); }
		public TextTextDecisionContext(TextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextTextDecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextTextDecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextTextDecision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextTextWordContext extends TextContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public TerminalNode Word() { return getToken(AutomaticTaskLanguageParser.Word, 0); }
		public TextTextWordContext(TextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextTextWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextTextWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextTextWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextWordContext extends TextContext {
		public TerminalNode Word() { return getToken(AutomaticTaskLanguageParser.Word, 0); }
		public TextWordContext(TextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextTextNumberContext extends TextContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public TextTextNumberContext(TextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterTextTextNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitTextTextNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitTextTextNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		return text(0);
	}

	private TextContext text(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TextContext _localctx = new TextContext(_ctx, _parentState);
		TextContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_text, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Decision:
				{
				_localctx = new TextDecisionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(129);
				match(Decision);
				}
				break;
			case Word:
				{
				_localctx = new TextWordContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(Word);
				}
				break;
			case Number:
				{
				_localctx = new TextNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(Number);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(142);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(140);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new TextTextWordContext(new TextContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_text);
						setState(134);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(135);
						match(Word);
						}
						break;
					case 2:
						{
						_localctx = new TextTextNumberContext(new TextContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_text);
						setState(136);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(137);
						match(Number);
						}
						break;
					case 3:
						{
						_localctx = new TextTextDecisionContext(new TextContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_text);
						setState(138);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(139);
						match(Decision);
						}
						break;
					}
					} 
				}
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FilexmlContext extends ParserRuleContext {
		public FilexmlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filexml; }
	 
		public FilexmlContext() { }
		public void copyFrom(FilexmlContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FileXmlFileNameContext extends FilexmlContext {
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public FileXmlFileNameContext(FilexmlContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileXmlFileName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileXmlFileName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileXmlFileName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FileXmlWithoutSlashContext extends FilexmlContext {
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode XML() { return getToken(AutomaticTaskLanguageParser.XML, 0); }
		public FileXmlWithoutSlashContext(FilexmlContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileXmlWithoutSlash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileXmlWithoutSlash(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileXmlWithoutSlash(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FileXmlWithSlashContext extends FilexmlContext {
		public FilexmlContext filexml() {
			return getRuleContext(FilexmlContext.class,0);
		}
		public TerminalNode DIV() { return getToken(AutomaticTaskLanguageParser.DIV, 0); }
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode XML() { return getToken(AutomaticTaskLanguageParser.XML, 0); }
		public FileXmlWithSlashContext(FilexmlContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileXmlWithSlash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileXmlWithSlash(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileXmlWithSlash(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilexmlContext filexml() throws RecognitionException {
		return filexml(0);
	}

	private FilexmlContext filexml(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FilexmlContext _localctx = new FilexmlContext(_ctx, _parentState);
		FilexmlContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_filexml, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new FileXmlFileNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(146);
				fileName(0);
				}
				break;
			case 2:
				{
				_localctx = new FileXmlWithoutSlashContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				fileName(0);
				setState(148);
				match(XML);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FileXmlWithSlashContext(new FilexmlContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_filexml);
					setState(152);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(153);
					match(DIV);
					setState(154);
					fileName(0);
					setState(155);
					match(XML);
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FileNameContext extends ParserRuleContext {
		public FileNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileName; }
	 
		public FileNameContext() { }
		public void copyFrom(FileNameContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FileNameWordContext extends FileNameContext {
		public TerminalNode Word() { return getToken(AutomaticTaskLanguageParser.Word, 0); }
		public FileNameWordContext(FileNameContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileNameWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileNameWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileNameWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FileNameWithWordContext extends FileNameContext {
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode Word() { return getToken(AutomaticTaskLanguageParser.Word, 0); }
		public FileNameWithWordContext(FileNameContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileNameWithWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileNameWithWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileNameWithWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FileNameWithNumberContext extends FileNameContext {
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public FileNameWithNumberContext(FileNameContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileNameWithNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileNameWithNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileNameWithNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FileNameNumberContext extends FileNameContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public FileNameNumberContext(FileNameContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileNameNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileNameNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileNameNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileNameContext fileName() throws RecognitionException {
		return fileName(0);
	}

	private FileNameContext fileName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FileNameContext _localctx = new FileNameContext(_ctx, _parentState);
		FileNameContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_fileName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Word:
				{
				_localctx = new FileNameWordContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(163);
				match(Word);
				}
				break;
			case Number:
				{
				_localctx = new FileNameNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164);
				match(Number);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(173);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(171);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new FileNameWithWordContext(new FileNameContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fileName);
						setState(167);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(168);
						match(Word);
						}
						break;
					case 2:
						{
						_localctx = new FileNameWithNumberContext(new FileNameContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fileName);
						setState(169);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(170);
						match(Number);
						}
						break;
					}
					} 
				}
				setState(175);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FormResponseContext extends ParserRuleContext {
		public RequestContext request() {
			return getRuleContext(RequestContext.class,0);
		}
		public ApprovalContext approval() {
			return getRuleContext(ApprovalContext.class,0);
		}
		public FormResponseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formResponse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFormResponse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFormResponse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFormResponse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormResponseContext formResponse() throws RecognitionException {
		FormResponseContext _localctx = new FormResponseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_formResponse);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				request();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				approval();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FileSearchContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public FileSearchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileSearch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterFileSearch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitFileSearch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitFileSearch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileSearchContext fileSearch() throws RecognitionException {
		FileSearchContext _localctx = new FileSearchContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fileSearch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__11);
			setState(181);
			match(T__12);
			setState(182);
			match(Number);
			setState(183);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalculationSearchContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public CalculationSearchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calculationSearch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterCalculationSearch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitCalculationSearch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitCalculationSearch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalculationSearchContext calculationSearch() throws RecognitionException {
		CalculationSearchContext _localctx = new CalculationSearchContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_calculationSearch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(T__11);
			setState(186);
			match(T__14);
			setState(187);
			match(Number);
			setState(188);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequestContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public RequestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_request; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterRequest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitRequest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitRequest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequestContext request() throws RecognitionException {
		RequestContext _localctx = new RequestContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_request);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(T__11);
			setState(191);
			match(T__15);
			setState(192);
			match(T__16);
			setState(193);
			match(Number);
			setState(194);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApprovalContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(AutomaticTaskLanguageParser.Number, 0); }
		public ApprovalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_approval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).enterApproval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskLanguageListener ) ((AutomaticTaskLanguageListener)listener).exitApproval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskLanguageVisitor) return ((AutomaticTaskLanguageVisitor<? extends T>)visitor).visitApproval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApprovalContext approval() throws RecognitionException {
		ApprovalContext _localctx = new ApprovalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_approval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__11);
			setState(197);
			match(T__15);
			setState(198);
			match(T__17);
			setState(199);
			match(Number);
			setState(200);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return prog_sempred((ProgContext)_localctx, predIndex);
		case 3:
			return arithmetic_sempred((ArithmeticContext)_localctx, predIndex);
		case 5:
			return filetext_sempred((FiletextContext)_localctx, predIndex);
		case 6:
			return emailtext_sempred((EmailtextContext)_localctx, predIndex);
		case 8:
			return text_sempred((TextContext)_localctx, predIndex);
		case 9:
			return filexml_sempred((FilexmlContext)_localctx, predIndex);
		case 10:
			return fileName_sempred((FileNameContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean prog_sempred(ProgContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean arithmetic_sempred(ArithmeticContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean filetext_sempred(FiletextContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean emailtext_sempred(EmailtextContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean text_sempred(TextContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		case 12:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean filexml_sempred(FilexmlContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean fileName_sempred(FileNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 4);
		case 15:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u00cd\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\5\2\'\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2/\n\2\f\2\16\2\62\13\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5I\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5T\n\5\f\5"+
		"\16\5W\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7n\n\7\f\7\16\7q\13\7\3\b\3\b\3\b\3\b\3\b"+
		"\7\bx\n\b\f\b\16\b{\13\b\3\t\3\t\3\t\3\t\5\t\u0081\n\t\3\n\3\n\3\n\3\n"+
		"\5\n\u0087\n\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u008f\n\n\f\n\16\n\u0092\13"+
		"\n\3\13\3\13\3\13\3\13\3\13\5\13\u0099\n\13\3\13\3\13\3\13\3\13\3\13\7"+
		"\13\u00a0\n\13\f\13\16\13\u00a3\13\13\3\f\3\f\3\f\5\f\u00a8\n\f\3\f\3"+
		"\f\3\f\3\f\7\f\u00ae\n\f\f\f\16\f\u00b1\13\f\3\r\3\r\5\r\u00b5\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\2\t\2\b\f\16\22\24\26\22\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\4\3\2\32\33\3\2\34\35\2\u00d8\2"+
		"&\3\2\2\2\4\63\3\2\2\2\6<\3\2\2\2\bH\3\2\2\2\nX\3\2\2\2\f`\3\2\2\2\16"+
		"r\3\2\2\2\20\u0080\3\2\2\2\22\u0086\3\2\2\2\24\u0098\3\2\2\2\26\u00a7"+
		"\3\2\2\2\30\u00b4\3\2\2\2\32\u00b6\3\2\2\2\34\u00bb\3\2\2\2\36\u00c0\3"+
		"\2\2\2 \u00c6\3\2\2\2\"#\b\2\1\2#\'\5\n\6\2$\'\5\4\3\2%\'\5\6\4\2&\"\3"+
		"\2\2\2&$\3\2\2\2&%\3\2\2\2\'\60\3\2\2\2()\f\b\2\2)/\5\n\6\2*+\f\7\2\2"+
		"+/\5\4\3\2,-\f\6\2\2-/\5\6\4\2.(\3\2\2\2.*\3\2\2\2.,\3\2\2\2/\62\3\2\2"+
		"\2\60.\3\2\2\2\60\61\3\2\2\2\61\3\3\2\2\2\62\60\3\2\2\2\63\64\7\3\2\2"+
		"\64\65\7\4\2\2\65\66\5\24\13\2\66\67\7\5\2\2\678\5\f\7\289\7\5\2\29:\5"+
		"\f\7\2:;\7\6\2\2;\5\3\2\2\2<=\7\7\2\2=>\7\5\2\2>?\5\b\5\2?@\7\6\2\2@\7"+
		"\3\2\2\2AB\b\5\1\2BI\5\20\t\2CI\7\30\2\2DE\7\b\2\2EF\5\b\5\2FG\7\t\2\2"+
		"GI\3\2\2\2HA\3\2\2\2HC\3\2\2\2HD\3\2\2\2IU\3\2\2\2JK\f\5\2\2KL\7\31\2"+
		"\2LT\5\b\5\6MN\f\4\2\2NO\t\2\2\2OT\5\b\5\5PQ\f\3\2\2QR\t\3\2\2RT\5\b\5"+
		"\4SJ\3\2\2\2SM\3\2\2\2SP\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\t\3\2"+
		"\2\2WU\3\2\2\2XY\7\n\2\2YZ\7\13\2\2Z[\7\f\2\2[\\\5\22\n\2\\]\7\f\2\2]"+
		"^\5\16\b\2^_\7\6\2\2_\13\3\2\2\2`a\b\7\1\2ab\5\22\n\2bo\3\2\2\2cd\f\6"+
		"\2\2dn\5\22\n\2ef\f\5\2\2fn\7\33\2\2gh\f\4\2\2hi\7\33\2\2ij\5\20\t\2j"+
		"k\7\r\2\2kl\5\20\t\2ln\3\2\2\2mc\3\2\2\2me\3\2\2\2mg\3\2\2\2nq\3\2\2\2"+
		"om\3\2\2\2op\3\2\2\2p\r\3\2\2\2qo\3\2\2\2rs\b\b\1\2st\5\20\t\2ty\3\2\2"+
		"\2uv\f\4\2\2vx\5\20\t\2wu\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\17\3"+
		"\2\2\2{y\3\2\2\2|\u0081\5\30\r\2}\u0081\5\32\16\2~\u0081\5\34\17\2\177"+
		"\u0081\5\22\n\2\u0080|\3\2\2\2\u0080}\3\2\2\2\u0080~\3\2\2\2\u0080\177"+
		"\3\2\2\2\u0081\21\3\2\2\2\u0082\u0083\b\n\1\2\u0083\u0087\7\25\2\2\u0084"+
		"\u0087\7\27\2\2\u0085\u0087\7\30\2\2\u0086\u0082\3\2\2\2\u0086\u0084\3"+
		"\2\2\2\u0086\u0085\3\2\2\2\u0087\u0090\3\2\2\2\u0088\u0089\f\b\2\2\u0089"+
		"\u008f\7\27\2\2\u008a\u008b\f\7\2\2\u008b\u008f\7\30\2\2\u008c\u008d\f"+
		"\6\2\2\u008d\u008f\7\25\2\2\u008e\u0088\3\2\2\2\u008e\u008a\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\23\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\b\13\1\2\u0094\u0099"+
		"\5\26\f\2\u0095\u0096\5\26\f\2\u0096\u0097\7\36\2\2\u0097\u0099\3\2\2"+
		"\2\u0098\u0093\3\2\2\2\u0098\u0095\3\2\2\2\u0099\u00a1\3\2\2\2\u009a\u009b"+
		"\f\5\2\2\u009b\u009c\7\33\2\2\u009c\u009d\5\26\f\2\u009d\u009e\7\36\2"+
		"\2\u009e\u00a0\3\2\2\2\u009f\u009a\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\25\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4"+
		"\u00a5\b\f\1\2\u00a5\u00a8\7\27\2\2\u00a6\u00a8\7\30\2\2\u00a7\u00a4\3"+
		"\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00af\3\2\2\2\u00a9\u00aa\f\6\2\2\u00aa"+
		"\u00ae\7\27\2\2\u00ab\u00ac\f\5\2\2\u00ac\u00ae\7\30\2\2\u00ad\u00a9\3"+
		"\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\27\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\5\36\20"+
		"\2\u00b3\u00b5\5 \21\2\u00b4\u00b2\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\31"+
		"\3\2\2\2\u00b6\u00b7\7\16\2\2\u00b7\u00b8\7\17\2\2\u00b8\u00b9\7\30\2"+
		"\2\u00b9\u00ba\7\20\2\2\u00ba\33\3\2\2\2\u00bb\u00bc\7\16\2\2\u00bc\u00bd"+
		"\7\21\2\2\u00bd\u00be\7\30\2\2\u00be\u00bf\7\20\2\2\u00bf\35\3\2\2\2\u00c0"+
		"\u00c1\7\16\2\2\u00c1\u00c2\7\22\2\2\u00c2\u00c3\7\23\2\2\u00c3\u00c4"+
		"\7\30\2\2\u00c4\u00c5\7\20\2\2\u00c5\37\3\2\2\2\u00c6\u00c7\7\16\2\2\u00c7"+
		"\u00c8\7\22\2\2\u00c8\u00c9\7\24\2\2\u00c9\u00ca\7\30\2\2\u00ca\u00cb"+
		"\7\20\2\2\u00cb!\3\2\2\2\25&.\60HSUmoy\u0080\u0086\u008e\u0090\u0098\u00a1"+
		"\u00a7\u00ad\u00af\u00b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}