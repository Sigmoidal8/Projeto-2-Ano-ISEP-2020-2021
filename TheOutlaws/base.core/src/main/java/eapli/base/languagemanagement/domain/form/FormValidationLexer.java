package eapli.base.languagemanagement.domain.form;// Generated from FormValidation.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormValidationLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, StringState=11, LogicOperator=12, BoolValue=13, EqualOrNot=14, 
		ConditionOperator=15, OptionType=16, MathOperator=17, Mandatory=18, String=19, 
		Integer=20, WS=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "StringState", "LogicOperator", "BoolValue", "EqualOrNot", "ConditionOperator", 
			"OptionType", "MathOperator", "Mandatory", "String", "Integer", "WS"
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


	public FormValidationLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FormValidation.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00c2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fc\n\f\3\r\3\r"+
		"\3\r\3\r\3\r\5\rj\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"u\n\16\3\17\3\17\3\17\3\17\5\17{\n\17\3\20\3\20\3\20\3\20\5\20\u0081\n"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\5\21\u0094\n\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u00b0\n\23\3\24\6\24\u00b3\n\24\r\24\16"+
		"\24\u00b4\3\25\6\25\u00b8\n\25\r\25\16\25\u00b9\3\26\6\26\u00bd\n\26\r"+
		"\26\16\26\u00be\3\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27\3\2\7\4"+
		"\2>>@@\5\2,-//\61\61\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2\u00cd\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\60\3\2\2\2\7"+
		"\65\3\2\2\2\t\67\3\2\2\2\13?\3\2\2\2\rJ\3\2\2\2\17M\3\2\2\2\21O\3\2\2"+
		"\2\23Q\3\2\2\2\25S\3\2\2\2\27b\3\2\2\2\31i\3\2\2\2\33t\3\2\2\2\35z\3\2"+
		"\2\2\37\u0080\3\2\2\2!\u0093\3\2\2\2#\u0095\3\2\2\2%\u00af\3\2\2\2\'\u00b2"+
		"\3\2\2\2)\u00b7\3\2\2\2+\u00bc\3\2\2\2-.\7k\2\2./\7h\2\2/\4\3\2\2\2\60"+
		"\61\7v\2\2\61\62\7j\2\2\62\63\7g\2\2\63\64\7p\2\2\64\6\3\2\2\2\65\66\7"+
		"=\2\2\66\b\3\2\2\2\678\7u\2\289\7k\2\29:\7|\2\2:;\7g\2\2;<\7\"\2\2<=\7"+
		"k\2\2=>\7u\2\2>\n\3\2\2\2?@\7k\2\2@A\7u\2\2AB\7\"\2\2BC\7d\2\2CD\7g\2"+
		"\2DE\7v\2\2EF\7y\2\2FG\7g\2\2GH\7g\2\2HI\7p\2\2I\f\3\2\2\2JK\7k\2\2KL"+
		"\7u\2\2L\16\3\2\2\2MN\7<\2\2N\20\3\2\2\2OP\7$\2\2P\22\3\2\2\2QR\7\60\2"+
		"\2R\24\3\2\2\2ST\7c\2\2TU\7p\2\2UV\7f\2\2V\26\3\2\2\2WX\7h\2\2XY\7k\2"+
		"\2YZ\7n\2\2Z[\7n\2\2[\\\7g\2\2\\c\7f\2\2]^\7g\2\2^_\7o\2\2_`\7r\2\2`a"+
		"\7v\2\2ac\7{\2\2bW\3\2\2\2b]\3\2\2\2c\30\3\2\2\2dj\t\2\2\2ef\7>\2\2fj"+
		"\7?\2\2gh\7@\2\2hj\7?\2\2id\3\2\2\2ie\3\2\2\2ig\3\2\2\2j\32\3\2\2\2kl"+
		"\7v\2\2lm\7t\2\2mn\7w\2\2nu\7g\2\2op\7h\2\2pq\7c\2\2qr\7n\2\2rs\7u\2\2"+
		"su\7g\2\2tk\3\2\2\2to\3\2\2\2u\34\3\2\2\2vw\7?\2\2w{\7?\2\2xy\7#\2\2y"+
		"{\7?\2\2zv\3\2\2\2zx\3\2\2\2{\36\3\2\2\2|}\7(\2\2}\u0081\7(\2\2~\177\7"+
		"~\2\2\177\u0081\7~\2\2\u0080|\3\2\2\2\u0080~\3\2\2\2\u0081 \3\2\2\2\u0082"+
		"\u0083\7K\2\2\u0083\u0084\7p\2\2\u0084\u0085\7v\2\2\u0085\u0086\7g\2\2"+
		"\u0086\u0087\7i\2\2\u0087\u0088\7g\2\2\u0088\u0094\7t\2\2\u0089\u008a"+
		"\7T\2\2\u008a\u008b\7g\2\2\u008b\u008c\7c\2\2\u008c\u0094\7n\2\2\u008d"+
		"\u008e\7U\2\2\u008e\u008f\7v\2\2\u008f\u0090\7t\2\2\u0090\u0091\7k\2\2"+
		"\u0091\u0092\7p\2\2\u0092\u0094\7i\2\2\u0093\u0082\3\2\2\2\u0093\u0089"+
		"\3\2\2\2\u0093\u008d\3\2\2\2\u0094\"\3\2\2\2\u0095\u0096\t\3\2\2\u0096"+
		"$\3\2\2\2\u0097\u0098\7q\2\2\u0098\u0099\7d\2\2\u0099\u009a\7n\2\2\u009a"+
		"\u009b\7k\2\2\u009b\u009c\7i\2\2\u009c\u009d\7c\2\2\u009d\u009e\7v\2\2"+
		"\u009e\u009f\7q\2\2\u009f\u00a0\7t\2\2\u00a0\u00b0\7{\2\2\u00a1\u00a2"+
		"\7p\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7\"\2\2\u00a5"+
		"\u00a6\7q\2\2\u00a6\u00a7\7d\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7k\2\2"+
		"\u00a9\u00aa\7i\2\2\u00aa\u00ab\7c\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad"+
		"\7q\2\2\u00ad\u00ae\7t\2\2\u00ae\u00b0\7{\2\2\u00af\u0097\3\2\2\2\u00af"+
		"\u00a1\3\2\2\2\u00b0&\3\2\2\2\u00b1\u00b3\t\4\2\2\u00b2\u00b1\3\2\2\2"+
		"\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5(\3"+
		"\2\2\2\u00b6\u00b8\t\5\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba*\3\2\2\2\u00bb\u00bd\t\6\2\2"+
		"\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\b\26\2\2\u00c1,\3\2\2\2\r\2b"+
		"itz\u0080\u0093\u00af\u00b4\u00b9\u00be\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}