// Generated from C:/Users/nicky/Desktop/Compilers/Compilers-Operating-Systems/compiler/Compiler Assignment/src\OurLanguage.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OurLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, INT=20, STRING=21, BOOLEAN=22, IDENTIFIER=23, WS=24, 
		COMMENT=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "INT", "STRING", "BOOLEAN", "IDENTIFIER", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'IF'", "';'", "'INT'", "'STRING'", "'='", "'PRINT'", "'{'", "'}'", 
			"'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'('", "')'", "'-'", "'*'", 
			"'+'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "INT", "STRING", "BOOLEAN", 
			"IDENTIFIER", "WS", "COMMENT"
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


	public OurLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "OurLanguage.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00a7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\7\25o\n\25\f\25"+
		"\16\25r\13\25\5\25t\n\25\3\26\3\26\7\26x\n\26\f\26\16\26{\13\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0088\n\27\3\30"+
		"\3\30\7\30\u008c\n\30\f\30\16\30\u008f\13\30\3\31\6\31\u0092\n\31\r\31"+
		"\16\31\u0093\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u009c\n\32\f\32\16\32"+
		"\u009f\13\32\3\32\6\32\u00a2\n\32\r\32\16\32\u00a3\3\32\3\32\3\u009d\2"+
		"\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\3\2\b\3\2\63;\3"+
		"\2\62;\4\2\f\f\17\17\4\2C\\c|\5\2C\\aac|\5\2\13\f\17\17\"\"\2\u00ae\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\58\3\2\2\2\7:\3\2\2\2\t>\3\2\2"+
		"\2\13E\3\2\2\2\rG\3\2\2\2\17M\3\2\2\2\21O\3\2\2\2\23Q\3\2\2\2\25S\3\2"+
		"\2\2\27U\3\2\2\2\31X\3\2\2\2\33[\3\2\2\2\35^\3\2\2\2\37a\3\2\2\2!c\3\2"+
		"\2\2#e\3\2\2\2%g\3\2\2\2\'i\3\2\2\2)s\3\2\2\2+u\3\2\2\2-\u0087\3\2\2\2"+
		"/\u0089\3\2\2\2\61\u0091\3\2\2\2\63\u0097\3\2\2\2\65\66\7K\2\2\66\67\7"+
		"H\2\2\67\4\3\2\2\289\7=\2\29\6\3\2\2\2:;\7K\2\2;<\7P\2\2<=\7V\2\2=\b\3"+
		"\2\2\2>?\7U\2\2?@\7V\2\2@A\7T\2\2AB\7K\2\2BC\7P\2\2CD\7I\2\2D\n\3\2\2"+
		"\2EF\7?\2\2F\f\3\2\2\2GH\7R\2\2HI\7T\2\2IJ\7K\2\2JK\7P\2\2KL\7V\2\2L\16"+
		"\3\2\2\2MN\7}\2\2N\20\3\2\2\2OP\7\177\2\2P\22\3\2\2\2QR\7>\2\2R\24\3\2"+
		"\2\2ST\7@\2\2T\26\3\2\2\2UV\7>\2\2VW\7?\2\2W\30\3\2\2\2XY\7@\2\2YZ\7?"+
		"\2\2Z\32\3\2\2\2[\\\7?\2\2\\]\7?\2\2]\34\3\2\2\2^_\7#\2\2_`\7?\2\2`\36"+
		"\3\2\2\2ab\7*\2\2b \3\2\2\2cd\7+\2\2d\"\3\2\2\2ef\7/\2\2f$\3\2\2\2gh\7"+
		",\2\2h&\3\2\2\2ij\7-\2\2j(\3\2\2\2kt\7\62\2\2lp\t\2\2\2mo\t\3\2\2nm\3"+
		"\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qt\3\2\2\2rp\3\2\2\2sk\3\2\2\2sl\3"+
		"\2\2\2t*\3\2\2\2uy\7$\2\2vx\n\4\2\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3"+
		"\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7$\2\2},\3\2\2\2~\177\7v\2\2\177\u0080\7"+
		"t\2\2\u0080\u0081\7w\2\2\u0081\u0088\7g\2\2\u0082\u0083\7h\2\2\u0083\u0084"+
		"\7c\2\2\u0084\u0085\7n\2\2\u0085\u0086\7u\2\2\u0086\u0088\7g\2\2\u0087"+
		"~\3\2\2\2\u0087\u0082\3\2\2\2\u0088.\3\2\2\2\u0089\u008d\t\5\2\2\u008a"+
		"\u008c\t\6\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\60\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0092"+
		"\t\7\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\31\2\2\u0096\62\3\2\2"+
		"\2\u0097\u0098\7\61\2\2\u0098\u0099\7\61\2\2\u0099\u009d\3\2\2\2\u009a"+
		"\u009c\13\2\2\2\u009b\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009e\3"+
		"\2\2\2\u009d\u009b\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u00a0"+
		"\u00a2\t\4\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\32\2\2\u00a6"+
		"\64\3\2\2\2\13\2psy\u0087\u008d\u0093\u009d\u00a3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}