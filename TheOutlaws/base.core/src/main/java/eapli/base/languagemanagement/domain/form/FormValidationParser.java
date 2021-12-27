package eapli.base.languagemanagement.domain.form;// Generated from FormValidation.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormValidationParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, StringState=11, LogicOperator=12, BoolValue=13, EqualOrNot=14, 
		ConditionOperator=15, OptionType=16, MathOperator=17, Mandatory=18, String=19, 
		Integer=20, WS=21;
	public static final int
		RULE_prog = 0, RULE_ifVerification = 1, RULE_content = 2, RULE_config = 3, 
		RULE_contentconditions = 4, RULE_conditions = 5, RULE_condition = 6, RULE_stringCondition = 7, 
		RULE_stringQuoteMarks = 8, RULE_stringValue = 9, RULE_intExpression = 10, 
		RULE_intValue = 11, RULE_gap = 12, RULE_declaration = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "ifVerification", "content", "config", "contentconditions", "conditions", 
			"condition", "stringCondition", "stringQuoteMarks", "stringValue", "intExpression", 
			"intValue", "gap", "declaration"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'then'", "';'", "'size is'", "'is between'", "'is'", "':'", 
			"'\"'", "'.'", "'and'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "StringState", 
			"LogicOperator", "BoolValue", "EqualOrNot", "ConditionOperator", "OptionType", 
			"MathOperator", "Mandatory", "String", "Integer", "WS"
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
	public String getGrammarFileName() { return "FormValidation.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormValidationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public IfVerificationContext ifVerification() {
			return getRuleContext(IfVerificationContext.class,0);
		}
		public ConfigContext config() {
			return getRuleContext(ConfigContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitProg(this);
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
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(35);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(33);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new ProgContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_prog);
						setState(29);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(30);
						ifVerification();
						}
						break;
					case 2:
						{
						_localctx = new ProgContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_prog);
						setState(31);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(32);
						config();
						}
						break;
					}
					} 
				}
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class IfVerificationContext extends ParserRuleContext {
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public IfVerificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifVerification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterIfVerification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitIfVerification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitIfVerification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfVerificationContext ifVerification() throws RecognitionException {
		IfVerificationContext _localctx = new IfVerificationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ifVerification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__0);
			setState(39);
			conditions(0);
			setState(40);
			match(T__1);
			setState(41);
			content(0);
			setState(42);
			match(T__2);
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

	public static class ContentContext extends ParserRuleContext {
		public ContentconditionsContext contentconditions() {
			return getRuleContext(ContentconditionsContext.class,0);
		}
		public ConfigContext config() {
			return getRuleContext(ConfigContext.class,0);
		}
		public IfVerificationContext ifVerification() {
			return getRuleContext(IfVerificationContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		return content(0);
	}

	private ContentContext content(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ContentContext _localctx = new ContentContext(_ctx, _parentState);
		ContentContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_content, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(45);
				contentconditions();
				}
				break;
			case 2:
				{
				setState(46);
				config();
				}
				break;
			case 3:
				{
				setState(47);
				ifVerification();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ContentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_content);
						setState(50);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(51);
						contentconditions();
						}
						break;
					case 2:
						{
						_localctx = new ContentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_content);
						setState(52);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(53);
						config();
						}
						break;
					case 3:
						{
						_localctx = new ContentContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_content);
						setState(54);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(55);
						ifVerification();
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class ConfigContext extends ParserRuleContext {
		public ConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_config; }
	 
		public ConfigContext() { }
		public void copyFrom(ConfigContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ValueSizeIsContext extends ConfigContext {
		public List<TerminalNode> Integer() { return getTokens(FormValidationParser.Integer); }
		public TerminalNode Integer(int i) {
			return getToken(FormValidationParser.Integer, i);
		}
		public ValueSizeIsContext(ConfigContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueSizeIs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueSizeIs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueSizeIs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueIsBetweenContext extends ConfigContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public GapContext gap() {
			return getRuleContext(GapContext.class,0);
		}
		public ValueIsBetweenContext(ConfigContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueIsBetween(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueIsBetween(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueIsBetween(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueIsMandatoryContext extends ConfigContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public TerminalNode Mandatory() { return getToken(FormValidationParser.Mandatory, 0); }
		public ValueIsMandatoryContext(ConfigContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueIsMandatory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueIsMandatory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueIsMandatory(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueVerifyLogicOrEqualConfigContext extends ConfigContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public IntExpressionContext intExpression() {
			return getRuleContext(IntExpressionContext.class,0);
		}
		public TerminalNode LogicOperator() { return getToken(FormValidationParser.LogicOperator, 0); }
		public TerminalNode EqualOrNot() { return getToken(FormValidationParser.EqualOrNot, 0); }
		public ValueVerifyLogicOrEqualConfigContext(ConfigContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueVerifyLogicOrEqualConfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueVerifyLogicOrEqualConfig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueVerifyLogicOrEqualConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigContext config() throws RecognitionException {
		ConfigContext _localctx = new ConfigContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_config);
		int _la;
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new ValueSizeIsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(Integer);
				setState(62);
				match(T__3);
				setState(63);
				match(Integer);
				setState(64);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new ValueIsBetweenContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				match(Integer);
				setState(66);
				match(T__4);
				setState(67);
				gap();
				setState(68);
				match(T__2);
				}
				break;
			case 3:
				_localctx = new ValueIsMandatoryContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(Integer);
				setState(71);
				match(T__5);
				setState(72);
				match(Mandatory);
				setState(73);
				match(T__2);
				}
				break;
			case 4:
				_localctx = new ValueVerifyLogicOrEqualConfigContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				match(Integer);
				setState(75);
				_la = _input.LA(1);
				if ( !(_la==LogicOperator || _la==EqualOrNot) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(76);
				intExpression(0);
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

	public static class ContentconditionsContext extends ParserRuleContext {
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public ContentconditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contentconditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterContentconditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitContentconditions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitContentconditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentconditionsContext contentconditions() throws RecognitionException {
		ContentconditionsContext _localctx = new ContentconditionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_contentconditions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			conditions(0);
			setState(80);
			match(T__2);
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

	public static class ConditionsContext extends ParserRuleContext {
		public ConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions; }
	 
		public ConditionsContext() { }
		public void copyFrom(ConditionsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConditionsNoOperatorContext extends ConditionsContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConditionsNoOperatorContext(ConditionsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterConditionsNoOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitConditionsNoOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitConditionsNoOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionsOperatorContext extends ConditionsContext {
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public TerminalNode ConditionOperator() { return getToken(FormValidationParser.ConditionOperator, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConditionsOperatorContext(ConditionsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterConditionsOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitConditionsOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitConditionsOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionsContext conditions() throws RecognitionException {
		return conditions(0);
	}

	private ConditionsContext conditions(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionsContext _localctx = new ConditionsContext(_ctx, _parentState);
		ConditionsContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_conditions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ConditionsNoOperatorContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(83);
			condition();
			}
			_ctx.stop = _input.LT(-1);
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionsOperatorContext(new ConditionsContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_conditions);
					setState(85);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(86);
					match(ConditionOperator);
					setState(87);
					condition();
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ValueIsEqualStringContext extends ConditionContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public TerminalNode EqualOrNot() { return getToken(FormValidationParser.EqualOrNot, 0); }
		public StringConditionContext stringCondition() {
			return getRuleContext(StringConditionContext.class,0);
		}
		public ValueIsEqualStringContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueIsEqualString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueIsEqualString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueIsEqualString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanIsValueContext extends ConditionContext {
		public TerminalNode BoolValue() { return getToken(FormValidationParser.BoolValue, 0); }
		public TerminalNode EqualOrNot() { return getToken(FormValidationParser.EqualOrNot, 0); }
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public BooleanIsValueContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterBooleanIsValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitBooleanIsValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitBooleanIsValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueStateContext extends ConditionContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public TerminalNode StringState() { return getToken(FormValidationParser.StringState, 0); }
		public ValueStateContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueState(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueVerifyLogicOrEqualContext extends ConditionContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public IntExpressionContext intExpression() {
			return getRuleContext(IntExpressionContext.class,0);
		}
		public TerminalNode LogicOperator() { return getToken(FormValidationParser.LogicOperator, 0); }
		public TerminalNode EqualOrNot() { return getToken(FormValidationParser.EqualOrNot, 0); }
		public ValueVerifyLogicOrEqualContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueVerifyLogicOrEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueVerifyLogicOrEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueVerifyLogicOrEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueIsBooleanContext extends ConditionContext {
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public TerminalNode EqualOrNot() { return getToken(FormValidationParser.EqualOrNot, 0); }
		public TerminalNode BoolValue() { return getToken(FormValidationParser.BoolValue, 0); }
		public ValueIsBooleanContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterValueIsBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitValueIsBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitValueIsBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_condition);
		int _la;
		try {
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new ValueVerifyLogicOrEqualContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				match(Integer);
				setState(94);
				_la = _input.LA(1);
				if ( !(_la==LogicOperator || _la==EqualOrNot) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(95);
				intExpression(0);
				}
				break;
			case 2:
				_localctx = new ValueStateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(Integer);
				setState(97);
				match(T__6);
				setState(98);
				match(StringState);
				}
				break;
			case 3:
				_localctx = new ValueIsEqualStringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				match(Integer);
				setState(100);
				match(EqualOrNot);
				setState(101);
				stringCondition();
				}
				break;
			case 4:
				_localctx = new ValueIsBooleanContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
				match(Integer);
				setState(103);
				match(EqualOrNot);
				setState(104);
				match(BoolValue);
				}
				break;
			case 5:
				_localctx = new BooleanIsValueContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(105);
				match(BoolValue);
				setState(106);
				match(EqualOrNot);
				setState(107);
				match(Integer);
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

	public static class StringConditionContext extends ParserRuleContext {
		public StringQuoteMarksContext stringQuoteMarks() {
			return getRuleContext(StringQuoteMarksContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public StringConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterStringCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitStringCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitStringCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringConditionContext stringCondition() throws RecognitionException {
		StringConditionContext _localctx = new StringConditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stringCondition);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				stringQuoteMarks();
				}
				break;
			case String:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				stringValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class StringQuoteMarksContext extends ParserRuleContext {
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public StringQuoteMarksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringQuoteMarks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterStringQuoteMarks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitStringQuoteMarks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitStringQuoteMarks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringQuoteMarksContext stringQuoteMarks() throws RecognitionException {
		StringQuoteMarksContext _localctx = new StringQuoteMarksContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stringQuoteMarks);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(T__7);
			setState(115);
			stringValue();
			setState(116);
			match(T__7);
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

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(FormValidationParser.String, 0); }
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public StringValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitStringValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringValueContext stringValue() throws RecognitionException {
		StringValueContext _localctx = new StringValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stringValue);
		try {
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(String);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(String);
				setState(120);
				stringValue();
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

	public static class IntExpressionContext extends ParserRuleContext {
		public IntValueContext intValue() {
			return getRuleContext(IntValueContext.class,0);
		}
		public IntExpressionContext intExpression() {
			return getRuleContext(IntExpressionContext.class,0);
		}
		public TerminalNode MathOperator() { return getToken(FormValidationParser.MathOperator, 0); }
		public IntExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterIntExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitIntExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitIntExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntExpressionContext intExpression() throws RecognitionException {
		return intExpression(0);
	}

	private IntExpressionContext intExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IntExpressionContext _localctx = new IntExpressionContext(_ctx, _parentState);
		IntExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_intExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(124);
			intValue();
			}
			_ctx.stop = _input.LT(-1);
			setState(131);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new IntExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_intExpression);
					setState(126);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(127);
					match(MathOperator);
					setState(128);
					intValue();
					}
					} 
				}
				setState(133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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

	public static class IntValueContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(FormValidationParser.String, 0); }
		public TerminalNode Integer() { return getToken(FormValidationParser.Integer, 0); }
		public IntValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterIntValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitIntValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitIntValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntValueContext intValue() throws RecognitionException {
		IntValueContext _localctx = new IntValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_intValue);
		int _la;
		try {
			setState(140);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				match(String);
				{
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(135);
					match(T__8);
					}
				}

				}
				setState(138);
				match(Integer);
				}
				break;
			case Integer:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(Integer);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class GapContext extends ParserRuleContext {
		public List<TerminalNode> Integer() { return getTokens(FormValidationParser.Integer); }
		public TerminalNode Integer(int i) {
			return getToken(FormValidationParser.Integer, i);
		}
		public GapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterGap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitGap(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitGap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GapContext gap() throws RecognitionException {
		GapContext _localctx = new GapContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_gap);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(Integer);
			setState(143);
			match(T__9);
			setState(144);
			match(Integer);
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

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode EqualOrNot() { return getToken(FormValidationParser.EqualOrNot, 0); }
		public TerminalNode LogicOperator() { return getToken(FormValidationParser.LogicOperator, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationListener ) ((FormValidationListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationVisitor) return ((FormValidationVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_la = _input.LA(1);
			if ( !(_la==LogicOperator || _la==EqualOrNot) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		case 2:
			return content_sempred((ContentContext)_localctx, predIndex);
		case 5:
			return conditions_sempred((ConditionsContext)_localctx, predIndex);
		case 10:
			return intExpression_sempred((IntExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean prog_sempred(ProgContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean content_sempred(ContentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean conditions_sempred(ConditionsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean intExpression_sempred(IntExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u0097\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\7\2$\n\2"+
		"\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4\63\n\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5P\n\5\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\7\7[\n\7\f\7\16\7^\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bo\n\b\3\t\3\t\5\ts\n\t\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\5\13|\n\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0084\n\f\f\f"+
		"\16\f\u0087\13\f\3\r\3\r\5\r\u008b\n\r\3\r\3\r\5\r\u008f\n\r\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\2\6\2\6\f\26\20\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\2\3\4\2\16\16\20\20\2\u009c\2\36\3\2\2\2\4(\3\2\2\2\6\62\3\2\2"+
		"\2\bO\3\2\2\2\nQ\3\2\2\2\fT\3\2\2\2\16n\3\2\2\2\20r\3\2\2\2\22t\3\2\2"+
		"\2\24{\3\2\2\2\26}\3\2\2\2\30\u008e\3\2\2\2\32\u0090\3\2\2\2\34\u0094"+
		"\3\2\2\2\36%\b\2\1\2\37 \f\4\2\2 $\5\4\3\2!\"\f\3\2\2\"$\5\b\5\2#\37\3"+
		"\2\2\2#!\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2"+
		"()\7\3\2\2)*\5\f\7\2*+\7\4\2\2+,\5\6\4\2,-\7\5\2\2-\5\3\2\2\2./\b\4\1"+
		"\2/\63\5\n\6\2\60\63\5\b\5\2\61\63\5\4\3\2\62.\3\2\2\2\62\60\3\2\2\2\62"+
		"\61\3\2\2\2\63<\3\2\2\2\64\65\f\b\2\2\65;\5\n\6\2\66\67\f\7\2\2\67;\5"+
		"\b\5\289\f\6\2\29;\5\4\3\2:\64\3\2\2\2:\66\3\2\2\2:8\3\2\2\2;>\3\2\2\2"+
		"<:\3\2\2\2<=\3\2\2\2=\7\3\2\2\2><\3\2\2\2?@\7\26\2\2@A\7\6\2\2AB\7\26"+
		"\2\2BP\7\5\2\2CD\7\26\2\2DE\7\7\2\2EF\5\32\16\2FG\7\5\2\2GP\3\2\2\2HI"+
		"\7\26\2\2IJ\7\b\2\2JK\7\24\2\2KP\7\5\2\2LM\7\26\2\2MN\t\2\2\2NP\5\26\f"+
		"\2O?\3\2\2\2OC\3\2\2\2OH\3\2\2\2OL\3\2\2\2P\t\3\2\2\2QR\5\f\7\2RS\7\5"+
		"\2\2S\13\3\2\2\2TU\b\7\1\2UV\5\16\b\2V\\\3\2\2\2WX\f\3\2\2XY\7\21\2\2"+
		"Y[\5\16\b\2ZW\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\r\3\2\2\2^\\\3"+
		"\2\2\2_`\7\26\2\2`a\t\2\2\2ao\5\26\f\2bc\7\26\2\2cd\7\t\2\2do\7\r\2\2"+
		"ef\7\26\2\2fg\7\20\2\2go\5\20\t\2hi\7\26\2\2ij\7\20\2\2jo\7\17\2\2kl\7"+
		"\17\2\2lm\7\20\2\2mo\7\26\2\2n_\3\2\2\2nb\3\2\2\2ne\3\2\2\2nh\3\2\2\2"+
		"nk\3\2\2\2o\17\3\2\2\2ps\5\22\n\2qs\5\24\13\2rp\3\2\2\2rq\3\2\2\2s\21"+
		"\3\2\2\2tu\7\n\2\2uv\5\24\13\2vw\7\n\2\2w\23\3\2\2\2x|\7\25\2\2yz\7\25"+
		"\2\2z|\5\24\13\2{x\3\2\2\2{y\3\2\2\2|\25\3\2\2\2}~\b\f\1\2~\177\5\30\r"+
		"\2\177\u0085\3\2\2\2\u0080\u0081\f\3\2\2\u0081\u0082\7\23\2\2\u0082\u0084"+
		"\5\30\r\2\u0083\u0080\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\27\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008a"+
		"\7\25\2\2\u0089\u008b\7\13\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\u008f\7\26\2\2\u008d\u008f\7\26\2\2\u008e\u0088"+
		"\3\2\2\2\u008e\u008d\3\2\2\2\u008f\31\3\2\2\2\u0090\u0091\7\26\2\2\u0091"+
		"\u0092\7\f\2\2\u0092\u0093\7\26\2\2\u0093\33\3\2\2\2\u0094\u0095\t\2\2"+
		"\2\u0095\35\3\2\2\2\17#%\62:<O\\nr{\u0085\u008a\u008e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}